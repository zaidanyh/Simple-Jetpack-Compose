package com.zaidan.simplejetpackcompose.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zaidan.simplejetpackcompose.data.response.ArticlesItem

@Database(
    entities = [ArticlesItem::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}