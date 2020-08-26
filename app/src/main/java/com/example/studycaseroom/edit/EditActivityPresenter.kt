package com.example.studycaseroom.edit

import com.example.studycaseroom.database.Item
import com.example.studycaseroom.database.ItemDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditActivityPresenter (private val db: ItemDatabase, private val listener: Listener) {
    interface Listener {
        fun showEditSuccess()
        fun showEditFailed()
    }

    fun editItem(item: Item) {
        GlobalScope.launch {
            val rowUpdated = db.itemDao().updateItem(item)

            if (rowUpdated > 0) {
                listener.showEditSuccess()
            } else {
                listener.showEditFailed()
            }
        }
    }
}