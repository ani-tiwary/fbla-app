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
}