package com.hc.kotlinstudyexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hc.kotlinstudyexample.Basic.BasicMainActivity
import kotlinx.android.synthetic.main.activity_main.*

//https://juejin.im/post/5df4aabe6fb9a0161104c8eb#heading-21
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        h5Demo.setOnClickListener {
            startActivity(Intent(MainActivity@this,BasicMainActivity::class.java))
        }

    }
}