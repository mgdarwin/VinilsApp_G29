package com.example.vinyls_jetpack_application.ui

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.vinyls_jetpack_application.R
import com.example.vinyls_jetpack_application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //-----
        //val albumButton: Button = findViewById(R.id.album_button)
        //albumButton.setOnClickListener { botonClick() }
        //-----


        // Get the navigation host fragment from this Activity
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController
        // Make sure actions in the ActionBar get propagated to the NavController
        Log.d("act", navController.toString())
        setSupportActionBar(findViewById(R.id.my_toolbar))
        setupActionBarWithNavController(navController)

    }

    private fun botonClick(){
        Toast.makeText(this, "button clicked",
            Toast.LENGTH_SHORT).show()
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}