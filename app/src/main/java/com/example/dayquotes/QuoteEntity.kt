package com.example.dayquotes

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quote")
data class QuoteEntity(
    @PrimaryKey val id: Int,
    // adding a '?' makes it a nullable type, meaning it can be set to null and not generate an npe.
    val quote: String?,
    val author: String?
)
