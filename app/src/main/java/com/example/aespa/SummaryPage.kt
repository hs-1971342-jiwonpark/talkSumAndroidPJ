package com.example.aespa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class SummaryPage : AppCompatActivity() {
    private val viewModel by viewModels<ButtonViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary_page)
    }
}