package com.example.aespa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.aespa.databinding.ActivityCustomBinding

class Custom : AppCompatActivity() {
    val viewModel by viewModels<ButtonViewModel>()

    private val binding by lazy{
        ActivityCustomBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)



    }
}