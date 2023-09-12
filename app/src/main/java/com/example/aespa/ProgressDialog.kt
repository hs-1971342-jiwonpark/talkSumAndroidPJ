package com.example.aespa

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

class ProgressDialog
    constructor(context: Context) : Dialog(context){
        init {
            setCanceledOnTouchOutside(false)
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(R.layout.progressbar);
        }
    }