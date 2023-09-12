package com.example.aespa

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText

class SaveDialog(context: Context) {
    private val dialog = Dialog(context)

    fun myDig() {


        val edit = dialog.findViewById<EditText>(R.id.editTextText5)
        val btnOk = dialog.findViewById<Button>(R.id.savebutton)


        btnOk.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setContentView(R.layout.nextsavedialog)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()
    }
    interface ButtonClickListener {
        fun onClicked(fileName: String)
    }

    private lateinit var onClickedListener: ButtonClickListener

    fun setOnClickedListener(listener: ButtonClickListener) {
        onClickedListener = listener
    }
}