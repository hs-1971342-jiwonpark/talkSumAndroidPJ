package com.example.aespa

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
data class ImgItem (
     val imgUrl : Int,
     val imgState : Boolean,
     var imgUri : Uri?
)


enum class ItemEvent { ADD, UPDATE, DELETE }
class ButtonViewModel : ViewModel(){
     private val _selectedImage = MutableLiveData<Int>()
     val selectedImage: LiveData<Int> get() = _selectedImage
     var itemId = 0
     val loginable: MutableLiveData<Boolean> = MutableLiveData()



     val itemsListData = MutableLiveData<ArrayList<ImgItem>>()
     val items = ArrayList<ImgItem>()
     val itemClickEvent = MutableLiveData<Int>()
     var itemsEvent = ItemEvent.ADD
     var itemsEventPos = -1
     var itemLongClick = -1
     fun getItem(pos: Int) =  items[pos]
     val itemsSize
          get() = items.size

     fun addItem() {
          itemsEvent = ItemEvent.ADD
          itemsEventPos = itemsSize
          val item1 = ImgItem(R.drawable.aespaimg,true,null)
          items[items.size-1] = item1
          val item2 = ImgItem(R.layout.add_image,false,null)
          items.add(item2)
          itemsListData.value = items // let the observer know the livedata changed
     }

     fun updateItem(pos: Int, uri: Uri?) {
          itemsEvent = ItemEvent.UPDATE
          itemsEventPos = pos
          val item = ImgItem(0,true,uri)
          items[pos] = item
          itemsListData.value = items // 옵저버에게 라이브데이터가 변경된 것을 알리기 위해
     }

     fun deleteItem(pos: Int) {
          itemsEvent = ItemEvent.DELETE
          itemsEventPos = pos
          items.removeAt(pos)
          itemsListData.value = items
     }
     init{

          items.add(ImgItem(R.drawable.aespaimg, true,null))
          items.add(ImgItem(R.layout.add_image,false,null))
          itemsListData.value = items
     }

     fun setSelectedImage(imageResId: Int) {
          _selectedImage.value = imageResId
     }
     companion object {
          val imgIcon1 = R.drawable.add_image
          val imgIcon2 = R.drawable.aespaimg
     }


}