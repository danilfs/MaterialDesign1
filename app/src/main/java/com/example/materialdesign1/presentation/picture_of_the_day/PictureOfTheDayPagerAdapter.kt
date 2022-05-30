package com.example.materialdesign1.presentation.picture_of_the_day

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.materialdesign1.domain.model.PictureOfTheDayPreset

class PictureOfTheDayPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = PictureOfTheDayPreset.values().size

    override fun createFragment(position: Int): Fragment {
        val preset = PictureOfTheDayPreset.values()[position]
        return PictureOfTheDayFragment.newInstance(preset)
    }
}