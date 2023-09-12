package com.example.aespa

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class ImgItem2(
    val imgUrl2 : Int,
    val editText : String?,
    val imgState : Boolean,
    var imgUri: Uri?
)
enum class ItemEvent2 { ADD, UPDATE, DELETE }
class ButtonViewModel2 : ViewModel() {
    val itemsListData = MutableLiveData<ArrayList<ImgItem2>>()
    val items = ArrayList<ImgItem2>()
    val itemClickEvent = MutableLiveData<Int>()
    var itemsEvent = ItemEvent2.ADD
    var itemsEventPos = -1
    var itemLongClick = -1
    fun getItem(pos: Int) = items[pos]
    val itemsSize
        get() = items.size

    fun addItem() {
        itemsEvent = ItemEvent2.ADD
        itemsEventPos = itemsSize
        val item1 = ImgItem2(R.drawable.aespaimg,"내용",true,null)
        items[items.size-1] = item1
        val item2 = ImgItem2(R.layout.add_image,"내용",false,null)
        items.add(item2)
        itemsListData.value = items // let the observer know the livedata changed
    }
    fun updateItem(pos: Int, uri: Uri?) {
        itemsEvent = ItemEvent2.UPDATE
        itemsEventPos = pos
        val item = ImgItem2(0,"내용",true,uri)
        items[pos] = item
        itemsListData.value = items // 옵저버에게 라이브데이터가 변경된 것을 알리기 위해
    }

    fun deleteItem(pos: Int) {
        itemsEvent = ItemEvent2.DELETE
        itemsEventPos = pos
        items.removeAt(pos)
        itemsListData.value = items
    }


    init {
        items.add(ImgItem2(R.drawable.aespaimg, "내용",true,null))
        items.add(ImgItem2(R.layout.add_image,null,false,null))
        itemsListData.value = items
    }

    companion object {
        val imgIcon1 = R.drawable.add_image
        val imgIcon2 = R.drawable.aespaimg

    }
}

