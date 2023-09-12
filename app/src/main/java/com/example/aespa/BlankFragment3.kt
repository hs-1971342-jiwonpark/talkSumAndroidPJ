package com.example.aespa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.aespa.databinding.FragmentBlank3Binding

class BlankFragment3 : Fragment(R.layout.fragment_blank3) {
    private val viewModel by activityViewModels<ButtonViewModel>()
    private lateinit var binding: FragmentBlank3Binding // 바인딩 객체 선언
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBlank3Binding.inflate(inflater, container, false) // 뷰 바인딩 초기화



        binding.button2.setOnClickListener{
            val intent = Intent(requireContext(),Custom2::class.java)
            viewModel.selectedImage.observe(viewLifecycleOwner) { imageResId ->
                intent.putExtra("imgId",imageResId)
                intent.putExtra("itemId",viewModel.itemId)
            }
            startActivity(intent)
        }

        
        return binding.root // 뷰 계층 구조 반환
        
    }

}