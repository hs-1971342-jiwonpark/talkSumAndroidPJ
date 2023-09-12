package com.example.aespa

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aespa.databinding.Item2Binding
import com.example.aespa.databinding.Item3Binding
import com.example.aespa.databinding.ItemBinding

class case3 : Fragment(), ImageSelectedListener {
    private val viewModel by activityViewModels<ButtonViewModel3>()
    private lateinit var binding:Item3Binding// 바인딩 객체 선언
    private val REQUEST_IMAGE_PICK =1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = Item3Binding.inflate(inflater, container, false) // 뷰 바인딩 초기화
        val recyclerView = binding.todoList3
        val adapter = CustomAdapter3(viewModel,this)
        recyclerView.adapter = CustomAdapter3(viewModel,this)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        viewModel.itemClickEvent.observe(requireActivity()) { // 항목(뷰)을 클릭했을 때 변경되는 데이터 옵저버
            //갤러리이동
        }
        return binding.root // 뷰 계층 구조 반환
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            if (selectedImageUri != null) {
                onImageReceived(selectedImageUri, CustomAdapter2.lastSelectedPosition)
            }
        }
    }

    override fun onImageSelected(position: Int) {
        val pickImageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        CustomAdapter2.lastSelectedPosition = position
        startActivityForResult(pickImageIntent, REQUEST_IMAGE_PICK)
    }



    override fun onImageReceived(uri: Uri, position: Int) {
        viewModel.updateItem(position, uri)
        binding.todoList3.adapter?.notifyItemChanged(position)
    }
}