package com.example.dayquotes

import androidx.room.Database
import androidx.room.RoomDatabase

// Three conditions must be met: 1.Class must be annotated, i.e '@Database', 2. Abstract class must extend RoomDatabase, 3. Each DAO associated must define abstract
// methods with zero arguments and returns an instance of the DAO class.
@Database(entities = [QuoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}