package com.example.testtask.presenter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.testtask.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.briefcases_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.briefCaseFragment)
    }
}