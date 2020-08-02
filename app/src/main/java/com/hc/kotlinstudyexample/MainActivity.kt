package com.hc.kotlinstudyexample

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.Window
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.hc.kotlinstudyexample.Basic.BasicMainActivity
import com.hc.kotlinstudyexample.H5.H5MainActivity
import com.hc.kotlinstudyexample.animation.AnimationMain
import com.hc.kotlinstudyexample.todo.tasks.TasksActivity
import com.hc.kotlinstudyexample.transitionAnim.ActivityAnimFirst
import kotlinx.android.synthetic.main.activity_main.*

//https://juejin.im/post/5df4aabe6fb9a0161104c8eb#heading-21

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow() .requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        //退出时使用
        getWindow().setExitTransition(Explode());
        //第一次进入时使用
        getWindow().setEnterTransition(Explode());
        //再次进入时使用
        getWindow().setReenterTransition(Explode());
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView() {
        basicDemo.setOnClickListener {
            startActivity(Intent(MainActivity@this,BasicMainActivity::class.java))
        }

        transitionAnim.setOnClickListener {
            startActivity(Intent(MainActivity@this,ActivityAnimFirst::class.java))
        }


        btn_main_anim.setOnClickListener {
            startActivity(Intent(MainActivity@this,AnimationMain::class.java))
        }

        btn_h5.setOnClickListener {
            startActivity(Intent(MainActivity@this,H5MainActivity::class.java))
        }

        btn_kotlin_test.setOnClickListener {
            startActivity(Intent(MainActivity@this,TasksActivity::class.java))
        }
    }
}