package com.example.anikash

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.anikash.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONArray
import org.json.JSONObject
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var filename = "nameFile"

    private var currentPortfolio = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_portfolios
            )
        )
        // remove empty title bar

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    fun goToHome() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navController.navigate(R.id.navigation_home)

    }

    fun writeToFile(json: JSONObject) {
        val fileOutputStream: FileOutputStream = openFileOutput(filename, MODE_PRIVATE)
        val json = json
        fileOutputStream.write(json.toString().toByteArray())
        fileOutputStream.close()

        println("File location: /$filename")
    }

    fun readJSONFromFile(): JSONObject {
        return try {
            val fileInputStream = openFileInput(filename)
            val text = fileInputStream.bufferedReader().use { it.readText() }
            fileInputStream.close()
            JSONObject(text)
        } catch (e: Exception) {
            val json = JSONObject()
            json.put("portfolio", JSONArray())
            val portfolioArray = json.getJSONArray("portfolio")
            portfolioArray.put(JSONObject()
                .put("Name", "Martin Luther King Jr.")
                .put("Organization", "Civil Rights Movement")
                .put("Position", "Leader")
                .put("Education", "Boston University School of Theology")
                .put("Skills", "Speaking, Leadership, Activism")
                .put("Honors", "Noble Prize, NAACP Spingarn Medal, Presidential Medal of Freedom")
                .put("References", "Malcolm X, Rosa Parks, John F. Kennedy")
                .put("EmploymentStatus", "N/A")
                .put("imageURL", "https://upload.wikimedia.org/wikipedia/commons/0/05/Martin_Luther_King%2C_Jr..jpg")
            )
            portfolioArray.put(JSONObject()
                .put("Name", "Rosa Parks")
                .put("Organization", "Civil Rights Movement")
                .put("Position", "Activist")
                .put("Education", "Alabama State Teachers College")
                .put("Skills", "Activism, Leadership")
                .put("Honors", "Congressional Gold Medal, Presidential Medal of Freedom")
                .put("References", "Martin Luther King Jr., Malcolm X, John F. Kennedy")
                .put("EmploymentStatus", "N/A")
                .put("imageURL", "https://upload.wikimedia.org/wikipedia/commons/c/c4/Rosaparks.jpg")
            )
            portfolioArray.put(JSONObject()
                .put("Name", "Elon Musk")
                .put("Organization", "Tesla")
                .put("Position", "CEO")
                .put("Education", "University of Pennsylvania")
                .put("Skills", "Leadership, Engineering")
                .put("Honors", "Very Rich Man")
                .put("References", "Jeff Bezos, Bill Gates, Steve Jobs")
                .put("EmploymentStatus", "Employed")
                .put("imageURL", "https://upload.wikimedia.org/wikipedia/commons/9/99/Elon_Musk_Colorado_2022_%28cropped2%29.jpg")
            )
            writeToFile(json)
            JSONObject()
        }
    }

    fun appendPortfolio(data: JSONObject) {
        val json = readJSONFromFile()

        try {
            val portfolioArray = json.getJSONArray("portfolio")
            portfolioArray.put(data)
        } catch (e: Exception) {
            System.out.println("Exception: $e")

            json.put("portfolio", JSONArray())
            val portfolioArray = json.getJSONArray("portfolio")
            portfolioArray.put(data)
            json.put("portfolio", portfolioArray)
        }

        writeToFile(json)

        println("File location: /$filename")
    }

    fun listPortfolios(): ArrayList<JSONObject> {
        val json = readJSONFromFile()

        try {
            val portfolioArray = json.getJSONArray("portfolio")
            val portfolioList = ArrayList<JSONObject>()
            for (i in 0 until portfolioArray.length()) {
                portfolioList.add(portfolioArray.getJSONObject(i))
            }
            return portfolioList
        } catch (e: Exception) {
            json.put("portfolio", JSONArray())
            val portfolioList = ArrayList<JSONObject>()
            portfolioList.add(JSONObject())
            return portfolioList
        }
    }

    fun showPage(page: Int): Int {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        currentPortfolio = page

        // make sure the current portfolio is a possible portfolio
        val portfolioList = listPortfolios()
        if (currentPortfolio >= portfolioList.size) {
            currentPortfolio = portfolioList.size - 1
        }

        goToHome()
        return 0
    }

    fun getCurrentPortfolio(): Int {
        return currentPortfolio
    }
}