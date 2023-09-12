package com.example.aespa

import android.net.Uri
import androidx.lifecycle.MutableLiveData


data class saveData(
    val date : String,
    val name : String,
    val user_name : String,
    val content : String
    )


enum class event { ADD, UPDATE, DELETE }

class SaveViewModel {
    var itemsEventPos = -1 //
    var itemLongClick = -1 //길게 클릭
    var itemsEvent = event.ADD // 아이템 이벤트
    val itemsListData = MutableLiveData<ArrayList<saveData>>()
    //저장된 아이템의 변화 감지
    val items = ArrayList<saveData>()
    //아이템들이 들어있는 리스트
    val itemClickEvent = MutableLiveData<Int>()

    val itemSize get() = items.size
    //이벤트 발생 감지
    fun getItem(pos:Int) = items[pos]

    fun deleteItem(pos: Int) {
        itemsEvent = event.DELETE
        itemsEventPos = pos
        items.removeAt(pos)
        itemsListData.value = items
    }

    fun updateItem(pos: Int, saveData: saveData) {
        itemsEvent = event.UPDATE
        itemsEventPos = pos
        items[pos] = saveData
        itemsListData.value = items // 옵저버에게 라이브데이터가 변경된 것을 알리기 위해
    }

}