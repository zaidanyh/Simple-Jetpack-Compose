package com.zaidan.simplejetpackcompose.di

import android.content.Context
import androidx.room.Room
import com.zaidan.simplejetpackcompose.data.room.NewsDao
import com.zaidan.simplejetpackcompose.data.room.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, "news_database").build()

    @Provides
    fun provideNewsDao(db: NewsDatabase): NewsDao = db.newsDao()
}