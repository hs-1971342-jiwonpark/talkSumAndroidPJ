package com.example.aespa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter3(private val viewModel: ButtonViewModel3,private val imageSelectedListener: ImageSelectedListener) : RecyclerView.Adapter<CustomAdapter3.ViewHolder3>() {
    companion object {
        var lastSelectedPosition = -1
    }
    inner class ViewHolder3(private val view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.imageView5)
        val editText = view.findViewById<EditText>(R.id.editTextText)
        private val REQUEST_IMAGE_PICK = 1

        private fun fetchImageFromGallery(position: Int) {
            imageSelectedListener.onImageSelected(position)
        }
        init {
            imageView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    CustomAdapter2.lastSelectedPosition = position
                    if (!viewModel.items[position].imgState) {
                        viewModel.addItem()
                    } else {
                        fetchImageFromGallery(position) // position을 전달해 줍니다.
                    }
                    notifyDataSetChanged()
                }
            }
        }
        fun setContents(pos: Int){

            with(viewModel.items[pos]){
                if(viewModel.items[pos].imgUri==null) {
                    when (viewModel.items[pos].imgState) {
                        true -> imageView.setImageResource(ButtonViewModel2.imgIcon2)
                        false -> imageView.setImageResource(ButtonViewModel2.imgIcon1)
                    }
                }
                else{
                    when (viewModel.items[pos].imgState) {
                        true -> imageView.setImageURI(this.imgUri)
                        false -> imageView.setImageResource(ButtonViewModel2.imgIcon1)
                    }
                }
            }
        }

    }
    // ViewHolder 생성, ViewHolder 는 View 를 담는 상자
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder3 {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = layoutInflater.inflate(R.layout.recycle_item3_view, viewGroup, false)
        val viewHolder = ViewHolder3(view)
        view.setOnClickListener {
            viewModel.itemClickEvent.value = viewHolder.adapterPosition
        }
        return viewHolder
    }

    // ViewHolder 에 데이터 연결
    override fun onBindViewHolder(viewHolder: ViewHolder3, position: Int) {
        viewHolder.setContents(position)
    }
    override fun getItemCount() = viewModel.items.size
}