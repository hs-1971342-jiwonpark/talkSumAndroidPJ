package com.example.aespa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.aespa.databinding.ActivityCustomBinding
import com.example.aespa.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    val viewModel: ButtonViewModel by viewModels()
    private val binding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}