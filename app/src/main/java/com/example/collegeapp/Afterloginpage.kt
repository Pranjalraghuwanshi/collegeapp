package com.example.collegeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class AfterLoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_afterloginpage)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)
        openFragment(Homepagefragment()) // Open the default fragment

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    openFragment(Homepagefragment())
                    true
                }
                R.id.navigation_yourpost -> {openFragment(Homepagefragment()) // Replace with your actual fragment class
                    true
                }
                R.id.navigation_yourwork -> {
                    openFragment(Homepagefragment()) // Replace with your actual fragment class
                    true
                }
                else -> false
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentholder, fragment) // Use the correct container ID
            .commit()
    }
}