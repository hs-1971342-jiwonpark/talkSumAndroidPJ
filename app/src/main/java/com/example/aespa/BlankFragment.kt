package com.example.aespa
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.aespa.databinding.FragmentBlankBinding

class BlankFragment : Fragment(R.layout.fragment_blank) {
    private val viewModel by activityViewModels<ButtonViewModel>()
    private lateinit var binding: FragmentBlankBinding // 바인딩 객체 선언

    // onCreateView에서 뷰 바인딩 초기화 및 레이아웃 반환
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBlankBinding.inflate(inflater, container, false) // 뷰 바인딩 초기화


        binding.imageButton.setOnClickListener {
            val selectedImageResId = R.drawable.case3
            viewModel.setSelectedImage(selectedImageResId)
            viewModel.itemId=1
        }
        binding.imageButton1.setOnClickListener {
            val selectedImageResId = R.drawable.case2
            viewModel.setSelectedImage(selectedImageResId)
            viewModel.itemId=2
        }
        binding.imageButton2.setOnClickListener {
            val selectedImageResId = R.drawable.case1
            viewModel.setSelectedImage(selectedImageResId)
            viewModel.itemId=3
        }

        return binding.root // 뷰 계층 구조 반환
    }
}

