package com.zaidan.simplejetpackcompose.data.room

import androidx.room.*
import com.zaidan.simplejetpackcompose.data.response.ArticlesItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_entity")
    fun getFavNews(): Flow<List<ArticlesItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavNews(articlesItem: ArticlesItem)

    @Delete
    suspend fun deleteFavNews(articlesItem: ArticlesItem)
}