package com.example.materialdesign1.presentation.switch_theme


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialdesign1.R
import com.example.materialdesign1.databinding.FragmentSwitchThemeBinding
import com.example.materialdesign1.domain.model.ThemePreferencesHelper

class SwitchThemeFragment : Fragment() {

    private var _binding: FragmentSwitchThemeBinding? = null
    private val binding: FragmentSwitchThemeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSwitchThemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.simpleThemeButton.setOnClickListener(::onClickSwitchThemeButton)
        binding.caramelThemeButton.setOnClickListener(::onClickSwitchThemeButton)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClickSwitchThemeButton(button: View) {
        val themeId = when (button.id) {
            R.id.caramel_theme_button -> R.style.CaramelTheme
            else -> R.style.SimpleTheme
        }

        activity?.let { activity ->
            ThemePreferencesHelper.ThemePreferencesHelper.saveTheme(activity, themeId)
            activity.recreate()
        }
    }

}