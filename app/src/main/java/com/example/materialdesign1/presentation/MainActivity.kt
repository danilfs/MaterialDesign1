package com.example.materialdesign1.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign1.R
import com.example.materialdesign1.databinding.ActivityMainBinding
import com.example.materialdesign1.domain.model.ThemePreferencesHelper

import com.example.materialdesign1.presentation.picture_of_the_day_pager.PictureOfTheDayPagerFragment
import com.example.materialdesign1.presentation.switch_theme.SwitchThemeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTheme()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNav()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupTheme() {
        val themeId = ThemePreferencesHelper.ThemePreferencesHelper.readTheme(this)
        setTheme(themeId)
    }

    private fun setupBottomNav() {
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.picture_of_the_day_menu -> PictureOfTheDayPagerFragment()
                R.id.switch_theme_menu -> SwitchThemeFragment()
                else -> null
            }?.also { fragment ->
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .commit()
            }
            true
        }
    }

}