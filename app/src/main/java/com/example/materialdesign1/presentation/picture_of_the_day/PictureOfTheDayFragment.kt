package com.example.materialdesign1.presentation.picture_of_the_day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.materialdesign1.R
import com.example.materialdesign1.databinding.FragmentPictureOfTheDayBinding
import com.example.materialdesign1.presentation.ExplanationBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding get() = _binding!!

    private val viewModel: PictureOfTheDayViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.requestPictureOfTheDay(0)
        setupDaysAgoChipGroup()
        observeUiState()
    }

    private fun setupDaysAgoChipGroup() {
        binding.daysAgoChipGroup.setOnCheckedChangeListener { _, chipId ->
            when (chipId) {
                R.id.two_days_ago_chip -> viewModel.requestPictureOfTheDay(2)
                R.id.yesterday_chip -> viewModel.requestPictureOfTheDay(1)
                R.id.today_chip -> viewModel.requestPictureOfTheDay(0)
            }
        }
    }

    private fun observeUiState() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest { uiState ->
                binding.pictureLoadingProgressBar.isVisible = uiState.loading
                binding.pictureOfTheDayImageView.setOnClickListener(null)
                uiState.pictureOfTheDay?.let { pictureOfTheDay ->
                    binding.titleTextView.text = pictureOfTheDay.title
                    binding.pictureOfTheDayImageView.load(pictureOfTheDay.url)
                    binding.pictureOfTheDayImageView.setOnClickListener {
                        showExplanationBottomSheetFragment(pictureOfTheDay.explanation)
                    }
                }
                uiState.error?.let { error ->
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showExplanationBottomSheetFragment(content: String) {
        val header = getString(R.string.explanation)
        ExplanationBottomSheetFragment(header, content).show(parentFragmentManager, header)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}