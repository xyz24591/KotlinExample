package com.hc.kotlinstudyexample.kotlinStudy.net

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Author: wanghaichao
 * @Date: 2022/6/13
 * @Description:
 * @Copyright: all rights reserved.
 */
class ArticleRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.wanandroid.com/")
        .client(OkHttpClient.Builder().addInterceptor {
            it.proceed(it.request()).apply {
                Log.d("whcTag", "request $code")
            }
        }.build())
        // 将返回的数据转换为String
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun getArticle(): String {
        val articleApi = retrofit.create(ArticleApi::class.java)
        val response = articleApi.getHomeArticles(0)
        return response.data.datas.first().title
    }
}
