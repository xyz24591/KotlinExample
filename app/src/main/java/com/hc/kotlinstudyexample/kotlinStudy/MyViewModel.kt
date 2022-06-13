package com.hc.kotlinstudyexample.kotlinStudy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hc.kotlinstudyexample.kotlinStudy.net.ArticleRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

/**
 *
 * @author:        Created by hc
 * @date:          Create in 21:57 2022/6/5
 * @description:
 *
 */
class MyViewModel() : ViewModel (){
    var count :Int = 0
    val sharedFlow = MutableSharedFlow<String>()
    private val articleRepository = ArticleRepository()
    val articlesLiveData = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            sharedFlow.emit("Hello")
            sharedFlow.emit("SharedFlow")
        }
    }


    fun getArticle() {
        viewModelScope.launch {
            articlesLiveData.value = articleRepository.getArticle()
        }
    }




}