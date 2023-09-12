package com.example.aespa

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.aespa.databinding.FragmentLoginOkBinding
import com.example.aespa.databinding.LoginFragmentBinding
import com.kakao.sdk.user.UserApiClient

class LoginOkFragment : Fragment() {
    val viewModel: ButtonViewModel by activityViewModels()
    private lateinit var binding: FragmentLoginOkBinding // 바인딩 객체 선언
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginOkBinding.inflate(inflater, container, false) // 뷰 바인딩 초기화
        return binding.root // 뷰 계층 구조 반환
    }
}
