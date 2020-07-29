package com.hc.kotlinstudyexample.H5

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import com.hc.kotlinstudyexample.R

/**
 * Created by hcw  on 2020/7/29
 * 类描述：
 * all rights reserved
 */
class BottomUpDialog : Dialog {

    constructor(context:Context):this(context,0)

    constructor(context: Context,themeResId:Int):super(context,R.style.dlg_anim){
        //构造函数统一处理
        setContentView(R.layout.hg_bottom_dialog)
        //设置 dialog 显示位置
        window?.setGravity(Gravity.BOTTOM)
        //设置 dialog 宽高
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)




    }


}