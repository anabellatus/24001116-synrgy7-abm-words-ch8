package com.anabell.words

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.anabell.words.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        setUpNavigationWithAppBar()
    }

    private fun setUpNavigationWithAppBar(){
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_container) as NavHostFragment? ?: return
        setupActionBarWithNavController(host.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_container) as NavHostFragment
        return host.navController.navigateUp() || super.onSupportNavigateUp()
    }
}