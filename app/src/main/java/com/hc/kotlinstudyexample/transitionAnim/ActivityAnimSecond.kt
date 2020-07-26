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
import kotlinx.android.synthetic.main.activity_anim_second.*

/**
 * Created by hcw  on 2020/7/26
 * 类描述：
 * all rights reserved
 */
class ActivityAnimSecond : AppCompatActivity()  {

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
        
        setContentView(R.layout.activity_anim_second)

        initSystemScene()
    }


    /**
     * TransitionManager.beginDelayedTransition(sceneRoot) 来实现系统属性动画
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun initSystemScene() {
        vSquare.setOnClickListener {
            TransitionManager.beginDelayedTransition(sceneRoot)
            vSquare.layoutParams.apply {
                width = 200.px
                height = 200.px
            }.also {
                vSquare.layoutParams = it
            }
        }
    }


    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()
    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()


}