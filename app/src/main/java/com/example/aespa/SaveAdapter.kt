package com.example.aespa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SaveAdapter(private val viewModel: SaveViewModel) : RecyclerView.Adapter<SaveAdapter.ViewHolder>(){

    companion object{
        var lastSelectedPosition = -1
    }
    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        //세팅
        fun setContents(pos: Int){}
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = layoutInflater.inflate(R.layout.activity_save_list,viewGroup,false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener {
            viewModel.itemClickEvent.value = viewHolder.adapterPosition
        }
        return viewHolder
    }
    override fun getItemCount() = viewModel.items.size

    override fun onBindViewHolder(viewHolder: SaveAdapter.ViewHolder, position: Int) {
        viewHolder.setContents(position)
    }
}

