package com.example.aespa

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val viewModel: ButtonViewModel, private val imageSelectedListener: ImageSelectedListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    companion object {
        var lastSelectedPosition = -1
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.imageView3)
        private val REQUEST_IMAGE_PICK = 1

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

        private fun fetchImageFromGallery(position: Int) {
            imageSelectedListener.onImageSelected(position)
        }

        fun setContents(pos: Int) {

            with(viewModel.items[pos]) {
                if (viewModel.items[pos].imgUri == null) {
                    when (viewModel.items[pos].imgState) {
                        true -> imageView.setImageResource(ButtonViewModel2.imgIcon2)
                        false -> imageView.setImageResource(ButtonViewModel2.imgIcon1)
                    }
                } else {
                    when (viewModel.items[pos].imgState) {
                        true -> imageView.setImageURI(this.imgUri)
                        false -> imageView.setImageResource(ButtonViewModel2.imgIcon1)
                    }
                }
            }
        }
    }

    // ViewHolder 생성, ViewHolder 는 View 를 담는 상자
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = layoutInflater.inflate(R.layout.recycle_item_view, viewGroup, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener {
            viewModel.itemClickEvent.value = viewHolder.adapterPosition
        }
        return viewHolder
    }

    // ViewHolder 에 데이터 연결
    override fun onBindViewHolder(viewHolder: CustomAdapter.ViewHolder, position: Int) {
        viewHolder.setContents(position)
    }

    override fun getItemCount() = viewModel.items.size
}