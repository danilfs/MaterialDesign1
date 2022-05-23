package com.example.materialdesign1.presentation

import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.materialdesign1.domain.model.ThemePreferencesHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTheme()
        setContentView(R.layout.activity_main)
    }

    private fun setupTheme() {
        val themeId = ThemePreferencesHelper.readTheme(this)
        setTheme(themeId)
    }

}