package com.example.studycaseroom.add

import com.example.studycaseroom.database.Item
import com.example.studycaseroom.database.ItemDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddActivityPresenter (val db: ItemDatabase, val listener: Listener) {
    fun saveItem(item: Item){
        GlobalScope.launch {
            val totalSaved = db.itemDao().addItem(item)

            if (totalSaved > 0) {
                listener.showSaveSuccess()
            } else {
                listener.showSaveFailed()
            }
        }
    }

    interface Listener{
        fun showSaveSuccess()
        fun showSaveFailed()
    }
}