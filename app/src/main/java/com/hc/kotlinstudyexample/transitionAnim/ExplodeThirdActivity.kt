package com.hc.kotlinstudyexample.transitionAnim

import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.hc.kotlinstudyexample.R

/**
 * Created by hcw  on 2020/7/26
 * 类描述：
 * all rights reserved
 */
class ExplodeThirdActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        window.enterTransition = Fade()
        //从第三个退出的时候
        getWindow().setExitTransition( Explode())
        setContentView(R.layout.activity_explode_third)
    }
}