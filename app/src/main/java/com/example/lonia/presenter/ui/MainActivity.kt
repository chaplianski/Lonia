package com.example.lonia.presenter.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.lonia.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.window?.statusBarColor = ContextCompat.getColor(this,
            android.R.color.white
        )
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }

    @SuppressLint("ResourceType")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("MyLog", "Click back")
        if (item.itemId == android.R.id.home){

            val navHostFragment = supportFragmentManager.findFragmentById(R.id.briefcases_container_view) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.briefCaseFragment)
        }
        return true
    }
}