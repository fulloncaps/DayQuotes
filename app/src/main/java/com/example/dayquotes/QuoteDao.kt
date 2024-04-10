package com.example.dayquotes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDao {
    @Insert
    fun insertAll(vararg quotes: QuoteEntity)

    @Delete
    fun delete(quote: QuoteEntity)

    @Query("SELECT * FROM quote")
    fun getAll(): List<QuoteEntity>

}