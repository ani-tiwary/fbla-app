package com.example.anikash

import android.os.Bundle
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
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
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