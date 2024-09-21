package com.example.eventnest.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.eventnest.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Use if you want full-screen experience
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set up NavHostFragment and NavController
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Set up BottomNavigationView with NavController
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        // Set the default fragment if no saved instance
        if (savedInstanceState == null) {
            navController.navigate(R.id.eventsFragment)
        }

        // Optional: Handle back navigation with BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_events -> navController.navigate(R.id.eventsFragment)
                R.id.nav_profile -> navController.navigate(R.id.profileFragment)
              //  R.id.nav_notifications -> navController.navigate(R.id.notificationsFragment)
                else -> false
            }
            true
        }
    }
}
