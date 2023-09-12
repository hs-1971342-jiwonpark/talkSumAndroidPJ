package com.example.aespa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.aespa.databinding.FragmentBlank2Binding

class BlankFragment2 : Fragment(R.layout.fragment_blank2) {
    private val viewModel by activityViewModels<ButtonViewModel>()
    private lateinit var binding: FragmentBlank2Binding // 바인딩 객체 선언

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBlank2Binding.inflate(inflater, container, false) // 뷰 바인딩 초기화

        viewModel.selectedImage.observe(viewLifecycleOwner) { imageResId ->
            binding.imageView.setImageResource(imageResId)
        }

        return binding.root // 뷰 계층 구조 반환
    }
}
