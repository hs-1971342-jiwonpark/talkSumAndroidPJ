package com.example.aespa

import android.net.Uri

interface ImageSelectedListener {
    fun onImageSelected(requestCode: Int)
    fun onImageReceived(uri: Uri, position: Int)
}