package com.example.materialdesign1.presentation.picture_of_the_day

import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.text.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.materialdesign1.databinding.FragmentPictureOfTheDayBinding
import com.example.materialdesign1.domain.model.PictureOfTheDayPreset
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlin.random.Random

@AndroidEntryPoint
class PictureOfTheDayFragment() : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding get() = _binding!!

    private val viewModel: PictureOfTheDayViewModel by viewModels()

    private var preset: PictureOfTheDayPreset = PictureOfTheDayPreset.TODAY

    private var controller: Controller? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        parseArgs()
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Controller) {
            controller = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        controller = null
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
                    binding.explanationTextView.text = decorateText(pictureOfTheDay.explanation)
                    binding.pictureOfTheDayImageView.load(pictureOfTheDay.url)
                    controller?.onLoadedPictureOfTheDay()
                }
                uiState.error?.let { error ->
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun decorateText(text: String): Spanned = SpannableStringBuilder().apply {
        text.split(" ").forEach { word ->
            when (Random.nextInt(6)) {
                0 -> italic { append(word) }
                1 -> bold { append(word) }
                2 -> color(Random.nextInt()) { append(word) }
                3 -> backgroundColor(Random.nextInt()) { append(word) }
                4 -> underline { append(word) }
                5 -> scale(Random.nextDouble(3.0).toFloat()) {append(word)}
                else -> append(word)
            }
            append(" ")
        }
    }.toSpanned()

    companion object {
        private const val KEY_PRESET = "preset"

        fun newInstance(preset: PictureOfTheDayPreset) =
            PictureOfTheDayFragment().apply {
                arguments = bundleOf(KEY_PRESET to preset)
            }
    }

    interface Controller {
        fun onLoadedPictureOfTheDay()
    }

}