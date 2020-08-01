package com.hc.kotlinstudyexample.transitionAnim

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.transition.Transition
import android.transition.TransitionValues
import android.view.ViewGroup
import androidx.annotation.RequiresApi

/**
 * Created by hcw  on 2020/8/1
 * 类描述：
 * all rights reserved
 */


/**
 * 创建自定义 Transition 步骤
 * 1、captureValues() 方法中保存自己感兴趣的属性值
 * 2、craeteAnimator() 取出感兴趣的属性，创建属性动画
 */

@RequiresApi(Build.VERSION_CODES.KITKAT)
class CustomTransiton : Transition(){
    //key 是自己定义的，主要避免重复即可
    private val BACKGROUND_KEY = "com.hc.kotlinstudyexample.transitionAnim:CustomTransition:background"

    //前两个方法开始布局和结束布局用
//    override fun captureStartValues(p0: TransitionValues?) {
//        captureValues(p0 )
//    }
//
//    override fun captureEndValues(p0: TransitionValues?) {
//        captureValues(p0)
//    }
//
//    private fun captureValues(transitionValues: TransitionValues?){
//        transitionValues?.values!![BACKGROUND_KEY] = transitionValues?.view?.background
//    }
//
//
//    //属性动画,最后两个参数保存起始值和结束值，可以通过 key 获取
//    override fun createAnimator(sceneRoot: ViewGroup?, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {
//
//
//        if (startValues == null || endValues == null) {
//            return null
//        }
//
//        val startDrawable = startValues?.values!![BACKGROUND_KEY] as Drawable
//        val endDrawable = endValues?.values!![BACKGROUND_KEY] as Drawable
//        if (startDrawable is ColorDrawable && endDrawable is ColorDrawable) {
//            val startColor = startDrawable.color
//            val endColor = endDrawable.color
//            if (startColor != endColor) {
//                return ObjectAnimator.ofObject(ArgbEvaluator(), startColor, endColor).apply {
//                    addUpdateListener {
//                        endValues?.view!!.setBackgroundColor(it?.animatedValue as Int)
//                    }
//                    duration = 3000
//                }
//            }
//        }
//        return null
//    }


    override fun captureStartValues(transitionValues: TransitionValues?) {
        catureValues(transitionValues)
    }

    private fun catureValues(transitionValues: TransitionValues?) {
        transitionValues?.values!![BACKGROUND_KEY] = transitionValues?.view?.background
    }

    override fun captureEndValues(transitionValues: TransitionValues?) {
        catureValues(transitionValues)
    }

    override fun createAnimator(sceneRoot: ViewGroup?, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {
        if (startValues == null || endValues == null) {
            return null
        }

        val startDrawable = startValues?.values!![BACKGROUND_KEY] as Drawable
        val endDrawable = endValues?.values!![BACKGROUND_KEY] as Drawable
        if (startDrawable is ColorDrawable && endDrawable is ColorDrawable) {
            val startColor = startDrawable.color
            val endColor = endDrawable.color
            if (startColor != endColor) {
                return ObjectAnimator.ofObject(ArgbEvaluator(), startColor, endColor).apply {
                    addUpdateListener {
                        endValues?.view!!.setBackgroundColor(it?.animatedValue as Int)
                    }
                    duration = 3000
                }
            }
        }
        return null
    }


}
