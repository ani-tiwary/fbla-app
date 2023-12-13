package com.example.anikash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.anikash.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var filename = "nameFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
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

    fun appendPortfolio(json: JSONObject) {
        val json = readJSONFromFile()

        try {
            val portfolio = json.getJSONObject("portfolio")
            val portfolioArray = portfolio.getJSONArray("portfolio")
            portfolioArray.put(json)
        } catch (e: Exception) {
            val portfolio = JSONObject()
            val portfolioArray = portfolio.getJSONArray("portfolio")
            portfolioArray.put(json)
        }

        println("File location: /$filename")
    }

    fun listPortfolios(): ArrayList<JSONObject> {
        val json = readJSONFromFile()

        try {
            val portfolio = json.getJSONObject("portfolio")
            val portfolioArray = portfolio.getJSONArray("portfolio")
            val portfolioList = ArrayList<JSONObject>()
            for (i in 0 until portfolioArray.length()) {
                portfolioList.add(portfolioArray.getJSONObject(i))
            }
            return portfolioList
        } catch (e: Exception) {
            val portfolio = ArrayList<JSONObject>()
            portfolio.add(JSONObject())
            return portfolio
        }
    }
}