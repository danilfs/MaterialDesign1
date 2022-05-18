package com.example.materialdesign1.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.materialdesign1.databinding.FragmentExplanationBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExplanationBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentExplanationBottomSheetBinding? = null
    private val binding: FragmentExplanationBottomSheetBinding get() = _binding!!

    private lateinit var header: String
    private lateinit var content: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        parseArgs()
        _binding = FragmentExplanationBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.headerTextView.text = header
        binding.contentTextView.text = content
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        header = requireArguments().getString(KEY_HEADER)
            ?: throw IllegalArgumentException("Missing argument: $KEY_HEADER")
        content = requireArguments().getString(KEY_CONTENT)
            ?: throw IllegalArgumentException("Missing argument: $KEY_CONTENT")
    }

    companion object {
        private const val KEY_HEADER = "header"
        private const val KEY_CONTENT = "content"

        operator fun invoke(header: String, content: String) =
            ExplanationBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_HEADER, header)
                    putString(KEY_CONTENT, content)
                }
            }
    }
}