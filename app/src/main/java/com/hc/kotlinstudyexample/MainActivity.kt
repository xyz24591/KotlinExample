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
import com.hc.kotlinstudyexample.databinding.ActivityMainBinding
import com.hc.kotlinstudyexample.https.ActivityHttpsMain
import com.hc.kotlinstudyexample.kotlinStudy.KotlinStudyMain
import com.hc.kotlinstudyexample.todo.tasks.TasksActivity
import com.hc.kotlinstudyexample.transitionAnim.ActivityAnimFirst

//https://juejin.im/post/5df4aabe6fb9a0161104c8eb#heading-21

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow() .requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        //退出时使用
        getWindow().setExitTransition(Explode());
        //第一次进入时使用
        getWindow().setEnterTransition(Explode());
        //再次进入时使用
        getWindow().setReenterTransition(Explode());
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

    }

    private fun initView() {
        binding.basicDemo.setOnClickListener {
            startActivity(Intent(MainActivity@this,BasicMainActivity::class.java))
        }

        binding.transitionAnim.setOnClickListener {
            startActivity(Intent(MainActivity@this,ActivityAnimFirst::class.java))
        }


        binding.btnMainAnim.setOnClickListener {
            startActivity(Intent(MainActivity@this,AnimationMain::class.java))
        }

        binding.btnH5.setOnClickListener {
            startActivity(Intent(MainActivity@this,H5MainActivity::class.java))
        }

        binding.btnKotlinTest.setOnClickListener {
            startActivity(Intent(MainActivity@this,TasksActivity::class.java))
        }

        binding.btnKotlinHttps.setOnClickListener {
            startActivity(Intent(MainActivity@this,ActivityHttpsMain::class.java))
        }

        binding.btnKtStudy.setOnClickListener {
            startActivity(Intent(MainActivity@this,KotlinStudyMain::class.java))
        }

    }
}