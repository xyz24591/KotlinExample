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
import com.hc.kotlinstudyexample.databinding.ActivityAnimMainBinding

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

    private lateinit var binding:ActivityAnimMainBinding

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initSpringAnim()
    }

    private fun initSpringAnim() {
        //弹簧动画
        val springForce = SpringForce(0f).apply {
            setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)      //设置跳的高度
            setStiffness(SpringForce.STIFFNESS_HIGH)                    //设置弹性
        }

        xSpringAnimation = SpringAnimation(binding.ivSpring, DynamicAnimation.TRANSLATION_X).setSpring(springForce)
        ySpringAnimation = SpringAnimation(binding.ivSpring, DynamicAnimation.TRANSLATION_Y).setSpring(springForce)


        //拖动弹簧属性
        binding.ivSpring.setOnTouchListener { v, event ->
            val actionCode = event.action
            if (actionCode == MotionEvent.ACTION_DOWN) {
                xDiffLeft = event.rawX - binding.ivSpring.x
                yDiffTop = event.rawY - binding.ivSpring.y
                xSpringAnimation.cancel()
                ySpringAnimation.cancel()
            } else if (actionCode == MotionEvent.ACTION_MOVE) {
                binding.ivSpring.x = event.rawX - xDiffLeft!!
                binding.ivSpring.y = event.rawY - yDiffTop!!
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
        binding.btnImgSizeSpring .setOnClickListener {
            if (!changed) {
                SpringAnimation(binding.ivSpring, propertyCompat).setSpring(sForce.setFinalPosition(2f))
                    .setMinimumVisibleChange(0.00390625f)
                    .start()
            } else {
                SpringAnimation(binding.ivSpring, propertyCompat).setSpring(sForce.setFinalPosition(1f))
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
        val tvTextSpringAnimation = SpringAnimation(binding.tvSpringOne,
            DynamicAnimation.TRANSLATION_Y)
        val tvVideoSpringAnimation = SpringAnimation(binding.tvSpringTwo,
            DynamicAnimation.TRANSLATION_Y).apply {
            addUpdateListener { animation, value, velocity ->
                tvTextSpringAnimation.animateToFinalPosition(value)
            }
        }
        val tvPicSpringAnimation = SpringAnimation(binding.tvSpringThree,
            DynamicAnimation.TRANSLATION_Y).apply {
            spring = springForce2
            addUpdateListener { dynamicAnimation, value, velocity ->
                tvVideoSpringAnimation.animateToFinalPosition(value)
            }
        }
        var group_changed = false
        binding.btnChainedSpring.setOnClickListener {
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
        binding.btnCircleReveal.setOnClickListener {
            if (isTextShow) hideView() else showView()
            isTextShow = !isTextShow
        }


        binding.tvCrossContent.setOnClickListener {
            binding.tvCrossContent.apply {
                visibility = View.VISIBLE
                alpha = 0f

                //渐隐动画
                animate().alpha(1.0f)
                    .setDuration(mShortAnimationDuration.toLong())
                    .withEndAction {
                        binding.tvCrossContent.visibility = View.VISIBLE
                    }.start()
            }

            binding.loadingSpinner.animate()
                .alpha(0.0f)
                .setDuration(mShortAnimationDuration.toLong())
                .withEndAction {
                    binding.loadingSpinner.visibility = View.GONE
                }.start()

        }

    }



    private fun hideView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val cx = binding.text.width / 2
            val cy = binding.text.height / 2
            //val cy = 0

            val initRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

            //圆形揭露动画
            //view : 执行动画目标
            //centerX,centerY 圆形坐标
            //startRadius,endRadius 开始和结束时的圆半径
            //返回值是 animtor 对象
            val anim = ViewAnimationUtils.createCircularReveal(binding.text, cx, cy, initRadius, 20f)
            anim.duration = 2000
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    binding.text.visibility = View.INVISIBLE
                }
            })
            anim.start()
        } else {
            binding.text.visibility = View.INVISIBLE
        }
    }

    private fun showView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val cx = binding.text.width / 2
            val cy = binding.text.height / 2

            val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
            val anim = ViewAnimationUtils.createCircularReveal(binding.text, cx, cy, 0f, finalRadius)
            binding.text.visibility = View.VISIBLE
            anim.start()
        } else {
            binding.text.visibility = View.VISIBLE
        }
    }

}