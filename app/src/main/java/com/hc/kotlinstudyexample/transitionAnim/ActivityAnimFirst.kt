package com.hc.kotlinstudyexample.transitionAnim

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.*
import android.view.Gravity
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.hc.kotlinstudyexample.R
import kotlinx.android.synthetic.main.activity_anim_first.*


/**
 * Created by hcw  on 2020/7/26
 * 类描述：
 * all rights reserved
 */

//todo,explode
class ActivityAnimFirst : AppCompatActivity(){


    var isFirst = true
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Activity 过渡动画有 Explode、slide、Fade 三个
//        //1、在 setContentView 之前设置属性
//        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

//        // 2 、创建 Explode、Slide、 Fade 对象
//        val slide= Slide()
//        slide.slideEdge= Gravity.START
//        slide.duration=300//效果时长，一般Activity切换时间很短，不建议设置过长

//        //3、将过渡动画设置给 Window
//        //退出当前界面的过渡动画
//        window.exitTransition = slide
//        //进入当前界面的过渡动画
//        window.enterTransition = slide
//        //重新进入界面的过渡动画
//        window.reenterTransition = slide

//        4、  5.0 之前的过渡动画还是使用 res/anim/**.xml，然后 overridePendingTransition 来指定动画

//        5、如果第二个Activity在finish掉后，回到第一个Activity界面也想有过渡效果，就不要手动调用finish(),可以调用finishAfterTransition ()方法。

        getWindow() .requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        val explode: Transition =
            TransitionInflater.from(this).inflateTransition(R.transition.explode)
        //退出时使用
        getWindow().setExitTransition(Explode());
//第一次进入时使用
        getWindow().setEnterTransition(Explode());
//再次进入时使用
        getWindow().setReenterTransition(Explode());


        //也可以不使用代码，在 style 中添加如下代码
        //<item name="android:windowExitTransition">@transition/explode</item>
        //<item name="android:windowEnterAnimation">@transition/explode</item>
        //<item name="android:windowReenterTransition">@transition/explode</item>


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
//            window.allowEnterTransitionOverlap=false
//            Fade().apply {
//                duration = 300
//                 excludeTarget(android.R.id.statusBarBackground, true)
//                excludeTarget(android.R.id.navigationBarBackground, true)
//            }.also {
//                window.exitTransition = it
//                window.enterTransition = it
//                window.reenterTransition = it
//            }
//        }

        setContentView(R.layout.activity_anim_first)
        init()
        initLayoutAnim()
        initExplode()

    }


    private fun initExplode() {

        tvExplode.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//判断Android版本
                startActivity(
                    Intent(this, ExplodeTestActivity::class.java),
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                )
            } else {
                startActivity(Intent(this, ExplodeTestActivity::class.java))
            }
        }
    }


    /**
     * 布局过渡动画，是指同一个 Activity 里面的布局变化动画
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun initLayoutAnim() {
        //需要有相同的根布局
        val firstScene = Scene.getSceneForLayout(flContent, R.layout.layout_first_scene, this)
        val secondScene = Scene.getSceneForLayout(flContent, R.layout.layout_sencod_scene, this)
        tvLayoutAnim.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (isFirst) {
                    //完全随机的从不同方向展示
                    TransitionManager.go(secondScene, Explode())
                   // TransitionManager.go(firstScene, Slide(Gravity.TOP))

                }else{
                    TransitionManager.go(firstScene, Slide(Gravity.TOP))
                }
                isFirst=!isFirst
            }
        }

    }


    /**
     * 注意....
     * 如果第二个界面的布局是延迟加载的，那么跳转到第二个界面时，可能没有动画效果
     *
     * 1、在 setContentView 后面添加 (判断版本)
     *        postponeEnterTransition()
     *
     * 2、在第二个加载完毕之后，开始动画
     *
     *  startPostponedEnterTransition()
     */

    private fun init() {
        //1、共享元素动画需要有相同的 transitionName ，这个属性也可以通过代码设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivImage.transitionName="activityTransform"
        }

        ivImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//判断Android版本

                //2、共享元素通过 ActivityOptions 来设置 sharedElementName，Intent 中指定使用 这个 option
                //3、目标 Activity 不用任何操作只需要在 Layout 中指定 SharedElementName
              // val bundle = ActivityOptions.makeSceneTransitionAnimation(this, ivImage, "activityTransform").toBundle()


                //4、如果有多个元素需要做过渡动画，使用 Pair
                val imagePair=android.util.Pair<View,String>(ivImage,"activityTransform")
                val textPair=android.util.Pair<View,String>(tvText,"textTransform")
                val bundle = ActivityOptions.makeSceneTransitionAnimation(this, imagePair,textPair).toBundle()

             //   startActivity(Intent(this, ActivityAnimSecond::class.java), bundle)


                startActivity(
                    Intent(this, ActivityAnimSecond::class.java),
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                )



            } else {
                startActivity(Intent(this, ActivityAnimSecond::class.java))
            }
        }

    }
}