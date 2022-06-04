package com.hc.kotlinstudyexample.transitionAnim

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.hc.kotlinstudyexample.R
import com.hc.kotlinstudyexample.databinding.ActivityAnimSecondBinding

/**
 * Created by hcw  on 2020/7/26
 * 类描述：
 * all rights reserved
 */
class ActivityAnimSecond : AppCompatActivity()  {

    private lateinit var binding:ActivityAnimSecondBinding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        getWindow() .requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        val explode: Transition =
            TransitionInflater.from(this).inflateTransition(R.transition.explode)
        //退出时使用
        getWindow().setExitTransition(Explode());
//第一次进入时使用
        getWindow().setEnterTransition(Explode());
//再次进入时使用
        getWindow().setReenterTransition(Explode());

        binding = ActivityAnimSecondBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_anim_second)

        initSystemScene()
    }


    /**
     * TransitionManager.beginDelayedTransition(sceneRoot) 来实现系统属性动画
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun initSystemScene() {
        binding.vSquare.setOnClickListener {
            TransitionManager.beginDelayedTransition(  binding.sceneRoot)
            binding.vSquare.layoutParams.apply {
                width = 200.px
                height = 200.px
            }.also {
                binding. vSquare.layoutParams = it
            }
        }
    }


    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()
    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()


}