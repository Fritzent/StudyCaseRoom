package com.example.studycaseroom.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ItemDao {

    //TODO ini untuk handle ketika ada data yang memiliki nilai primary key yang sama untuk di timpah
    @Insert(onConflict = REPLACE)
    fun addItem(item: Item): Long

    @Query("SELECT * FROM Item ")
    fun readAllItem(): List<Item>

    @Update
    fun updateItem(item: Item): Int

    @Delete
    fun deleteItem(item: Item): Int
}