package com.example.aespa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aespa.databinding.ActivityCustom2Binding
import com.example.aespa.databinding.ActivitySaveListBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class SaveList : AppCompatActivity() {
    private val binding by lazy{
        ActivitySaveListBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_list)
    }
    val storage = Firebase.storage
    val storageRef = storage.reference



}