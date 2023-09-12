package com.example.aespa

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class ImgItem3(
    val imgUrl2 : Int,
    val editText : String?,
    val imgState : Boolean,
    var imgUri : Uri?
)
enum class ItemEvent3 { ADD, UPDATE, DELETE }
class ButtonViewModel3 : ViewModel() {
    val itemsListData = MutableLiveData<ArrayList<ImgItem3>>()
    val items = ArrayList<ImgItem3>()
    val itemClickEvent = MutableLiveData<Int>()
    var itemsEvent = ItemEvent3.ADD
    var itemsEventPos = -1
    var itemLongClick = -1
    fun getItem(pos: Int) = items[pos]
    val itemsSize
        get() = items.size

    fun addItem() {
        itemsEvent = ItemEvent3.ADD
        itemsEventPos = itemsSize
        val item1 = ImgItem3(R.drawable.aespaimg,"내용",true,null)
        items[items.size-1] = item1
        val item2 = ImgItem3(R.layout.add_image,"내용",false,null)
        items.add(item2)
        itemsListData.value = items // let the observer know the livedata changed
    }

    fun updateItem(pos: Int, uri : Uri?) {
        itemsEvent = ItemEvent3.UPDATE
        itemsEventPos = pos
        val item = ImgItem3(0,"내용",true,uri)
        items[pos] = item
        itemsListData.value = items // 옵저버에게 라이브데이터가 변경된 것을 알리기 위해
    }

    fun deleteItem(pos: Int) {
        itemsEvent = ItemEvent3.DELETE
        itemsEventPos = pos
        items.removeAt(pos)
        itemsListData.value = items
    }

    init {

        items.add(ImgItem3(R.drawable.aespaimg, "내용",true,null))
        items.add(ImgItem3(R.layout.add_image,null,false,null))
        itemsListData.value = items
    }

    companion object {
        val imgIcon1 = R.drawable.add_image
        val imgIcon2 = R.drawable.aespaimg
    }
}

