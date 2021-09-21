package com.zaidan.simplejetpackcompose.data

import com.zaidan.simplejetpackcompose.data.response.ArticlesItem
import com.zaidan.simplejetpackcompose.data.response.MainResponse
import com.zaidan.simplejetpackcompose.data.room.NewsDao
import com.zaidan.simplejetpackcompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.SocketTimeoutException
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val dao: NewsDao
) {
    fun getNews(category: String): Flow<Resource<MainResponse>> = flow {
        emit(Resource.loading(null))
        try {
            val response = apiInterface.gewNews(category)
            if (response.isSuccessful) {
                emit(Resource.success(response.body()))
            } else {
                emit(Resource.error(null, "Terjadi Kesalahan"))
            }
        } catch (timeOut: SocketTimeoutException) {
            emit(Resource.error(null, timeOut.message.toString()))
        }
    }

    fun favNews() = dao.getFavNews()
    suspend fun insertNews(news: ArticlesItem) = dao.insertFavNews(news)
    suspend fun deleteNews(news: ArticlesItem) = dao.deleteFavNews(news)
}