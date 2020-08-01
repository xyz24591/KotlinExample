package com.hc.kotlinstudyexample.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewAnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.hc.kotlinstudyexample.R
import kotlinx.android.synthetic.main.activity_anim_main.*

/**
 * Created by hcw  on 2020/8/1
 * 类描述：
 * all rights reserved
 */
class AnimationMain :AppCompatActivity() {

    private var isTextShow = true
    private var mShortAnimationDuration: Int = 5000

    //弹簧动画,基于物理的动画，需要引入相关依赖才行
    lateinit var xSpringAnimation: SpringAnimation
    lateinit var ySpringAnimation: SpringAnimation

    var xDiffLeft: Float? = null
    var yDiffTop: Float? = null


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_main)
        initView()
        initSpringAnim()
    }

    private fun initSpringAnim() {
        //弹簧动画
        val springForce = SpringForce(0f).apply {
            setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)      //设置跳的高度
            setStiffness(SpringForce.STIFFNESS_HIGH)                    //设置弹性
        }

        xSpringAnimation = SpringAnimation(iv_spring, DynamicAnimation.TRANSLATION_X).setSpring(springForce)
        ySpringAnimation = SpringAnimation(iv_spring, DynamicAnimation.TRANSLATION_Y).setSpring(springForce)


        //拖动弹簧属性
        iv_spring.setOnTouchListener { v, event ->
            val actionCode = event.action
            if (actionCode == MotionEvent.ACTION_DOWN) {
                xDiffLeft = event.rawX - iv_spring.x
                yDiffTop = event.rawY - iv_spring.y
                xSpringAnimation.cancel()
                ySpringAnimation.cancel()
            } else if (actionCode == MotionEvent.ACTION_MOVE) {
                iv_spring.x = event.rawX - xDiffLeft!!
                iv_spring.y = event.rawY - yDiffTop!!
            } else if (actionCode == MotionEvent.ACTION_UP) {
                xSpringAnimation.start()
                ySpringAnimation.start()
            }
            true
        }

        var changed = false
        val sForce = SpringForce()
            .setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
            .setStiffness(SpringForce.STIFFNESS_LOW)
        val propertyCompat = object : FloatPropertyCompat<View>("scale") {
            override fun setValue(view: View?, scale: Float) {
                view!!.scaleX = scale
                view!!.scaleY = scale
            }

            override fun getValue(view: View?): Float {
                return view!!.scaleX
            }
        }
        //设置大小改变的弹簧效果
        btn_img_size_spring.setOnClickListener {
            if (!changed) {
                SpringAnimation(iv_spring, propertyCompat).setSpring(sForce.setFinalPosition(2f))
                    .setMinimumVisibleChange(0.00390625f)
                    .start()
            } else {
                SpringAnimation(iv_spring, propertyCompat).setSpring(sForce.setFinalPosition(1f))
                    .setMinimumVisibleChange(0.00390625f)
                    .start()
            }
            changed = !changed
        }

        //组合弹性动画

        val springForce2 = SpringForce(-600f).apply {
            dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            stiffness = SpringForce.STIFFNESS_LOW
        }
        val tvTextSpringAnimation = SpringAnimation(tv_spring_one,
            DynamicAnimation.TRANSLATION_Y)
        val tvVideoSpringAnimation = SpringAnimation(tv_spring_two,
            DynamicAnimation.TRANSLATION_Y).apply {
            addUpdateListener { animation, value, velocity ->
                tvTextSpringAnimation.animateToFinalPosition(value)
            }
        }
        val tvPicSpringAnimation = SpringAnimation(tv_spring_three,
            DynamicAnimation.TRANSLATION_Y).apply {
            spring = springForce2
            addUpdateListener { dynamicAnimation, value, velocity ->
                tvVideoSpringAnimation.animateToFinalPosition(value)
            }
        }
        var group_changed = false
        btn_chained_spring.setOnClickListener {
            if (!group_changed) {
                tvPicSpringAnimation.start()
            } else {
                tvPicSpringAnimation.skipToEnd()
            }
            group_changed = !group_changed
        }


    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun initView() {
        btn_circle_reveal.setOnClickListener {
            if (isTextShow) hideView() else showView()
            isTextShow = !isTextShow
        }


        btn_cross_fade.setOnClickListener {
            tv_cross_content.apply {
                visibility = View.VISIBLE
                alpha = 0f

                //渐隐动画
                animate().alpha(1.0f)
                    .setDuration(mShortAnimationDuration.toLong())
                    .withEndAction {
                        tv_cross_content.visibility = View.VISIBLE
                    }.start()
            }

            loading_spinner.animate()
                .alpha(0.0f)
                .setDuration(mShortAnimationDuration.toLong())
                .withEndAction {
                    loading_spinner.visibility = View.GONE
                }.start()

        }

    }



    private fun hideView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val cx = text.width / 2
            val cy = text.height / 2
            //val cy = 0

            val initRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

            //圆形揭露动画
            //view : 执行动画目标
            //centerX,centerY 圆形坐标
            //startRadius,endRadius 开始和结束时的圆半径
            //返回值是 animtor 对象
            val anim = ViewAnimationUtils.createCircularReveal(text, cx, cy, initRadius, 20f)
            anim.duration = 2000
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    text.visibility = View.INVISIBLE
                }
            })
            anim.start()
        } else {
            text.visibility = View.INVISIBLE
        }
    }

    private fun showView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val cx = text.width / 2
            val cy = text.height / 2

            val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
            val anim = ViewAnimationUtils.createCircularReveal(text, cx, cy, 0f, finalRadius)
            text.visibility = View.VISIBLE
            anim.start()
        } else {
            text.visibility = View.VISIBLE
        }
    }

}