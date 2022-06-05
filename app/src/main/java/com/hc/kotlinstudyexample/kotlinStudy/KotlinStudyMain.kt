package com.hc.kotlinstudyexample.kotlinStudy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.hc.kotlinstudyexample.databinding.ActivityKotlinStudyMainBinding
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 *
 * @author:        Created by hc
 * @date:          Create in 21:49 2022/6/5
 * @description:
 *
 */
class KotlinStudyMain:AppCompatActivity() {
    private lateinit var binding : ActivityKotlinStudyMainBinding

    //private val myViewModel by viewModels<MyViewModel>()

    //viewmodel 使用
    private lateinit var viewModel: MyViewModel



    private lateinit var content:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityKotlinStudyMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
       // viewModel = ViewModelProvider(this,MyViewModelFactory(12)).get(MyViewModel::class.java)

        initView()

        lifecycleScope.launch{
         viewModel.sharedFlow.collect{
             println("whcTag:$it")
         }
        }
    }

    private fun initView() {
        binding.btnAdd.setOnClickListener {
           viewModel.count++
            binding.tvFirst.text = viewModel.count.toString()
        }
        binding.tvFirst.text = viewModel.count.toString()
    }

    //SharedFlow
    private fun sharedFlowTest(){

    }
}