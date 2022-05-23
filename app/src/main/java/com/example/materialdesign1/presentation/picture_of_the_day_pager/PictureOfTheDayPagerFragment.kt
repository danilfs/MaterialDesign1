package com.example.materialdesign1.presentation.picture_of_the_day_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialdesign1.databinding.FragmentPictureOfTheDayPagerBinding
import com.example.materialdesign1.domain.model.PictureOfTheDayPreset
import com.example.materialdesign1.presentation.picture_of_the_day.PictureOfTheDayPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class PictureOfTheDayPagerFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayPagerBinding? = null
    private val binding: FragmentPictureOfTheDayPagerBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            pictureOfTheDayViewpager.adapter = PictureOfTheDayPagerAdapter(this@PictureOfTheDayPagerFragment)
            TabLayoutMediator(pictureOfTheDayTabLayout, pictureOfTheDayViewpager) { tab, position ->
                tab.setText(PictureOfTheDayPreset.values()[position].title)
                //tab.text = "Tab"
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}