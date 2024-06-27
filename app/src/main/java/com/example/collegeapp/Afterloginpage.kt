package com.example.collegeapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AfterLoginPage : AppCompatActivity() {

    private lateinit var favAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_afterloginpage)

        favAddTask =findViewById(R.id.fab_add_task)
            favAddTask.setOnClickListener { showAddTaskDialog() }

        // Set up BottomNavigationView (this can stay here)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)
        openFragment(Homepagefragment()) // Open the default fragment

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    openFragment(Homepagefragment())
                    true
                }
                R.id.navigation_yourpost -> {
                    openFragment(yourpostfragment())
                    true
                }
                R.id.navigation_yourwork -> {
                    openFragment(yourworkfragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun showAddTaskDialog() {
        val dialog = CreateTaskDialog()
        dialog.show(supportFragmentManager, "create_task_dialog")
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentholder, fragment)
            .commit()
    }
}