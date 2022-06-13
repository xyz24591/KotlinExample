package com.hc.kotlinstudyexample.kotlinStudy.net

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author: wanghaichao
 * @Date: 2022/6/13
 * @Description:
 * @Copyright: all rights reserved.
 */
interface ArticleApi {

    //suspend 关键字，可以直接在协程中直接调用此 api 来获取返回数据
    @GET("article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): Response
}
