package com.hc.kotlinstudyexample.transitionAnim

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.hc.kotlinstudyexample.R
import kotlinx.android.synthetic.main.activity_explode_test.*


/**
 * Created by hcw  on 2020/7/26
 * 类描述：
 * all rights reserved
 */
class ExplodeTestActivity  : AppCompatActivity(){
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        window.enterTransition = Explode()
        //从第三个退出的时候
        getWindow().setExitTransition( Slide(Gravity.LEFT))

        setContentView(R.layout.activity_explode_test)

        third_hello.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//判断Android版本
                startActivity(
                    Intent(this, ExplodeThirdActivity::class.java),
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                )
            } else {
                startActivity(Intent(this, ExplodeThirdActivity::class.java))
            }
        }
    }
}