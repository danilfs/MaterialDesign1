package com.example.materialdesign1.presentation.picture_of_the_day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.materialdesign1.databinding.FragmentPictureOfTheDayBinding
import com.example.materialdesign1.domain.model.PictureOfTheDayPreset
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PictureOfTheDayFragment() : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding get() = _binding!!

    private val viewModel: PictureOfTheDayViewModel by viewModels()

    private var preset: PictureOfTheDayPreset = PictureOfTheDayPreset.TODAY

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        parseArgs()
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.requestPictureOfTheDay(preset.daysAgo)
        observeUiState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        preset = requireArguments().getSerializable(KEY_PRESET) as PictureOfTheDayPreset
    }

    private fun observeUiState() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest { uiState ->
                binding.pictureLoadingProgressBar.isVisible = uiState.loading
                binding.pictureOfTheDayImageView.setOnClickListener(null)
                uiState.pictureOfTheDay?.let { pictureOfTheDay ->
                    binding.toolbar.title = pictureOfTheDay.title
                    binding.explanationTextView.text = pictureOfTheDay.explanation
                    binding.pictureOfTheDayImageView.load(pictureOfTheDay.url)
                }
                uiState.error?.let { error ->
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        private const val KEY_PRESET = "preset"

        fun newInstance(preset: PictureOfTheDayPreset) =
            PictureOfTheDayFragment().apply {
                arguments = bundleOf(KEY_PRESET to preset)
            }
    }

}