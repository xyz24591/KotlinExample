package com.hc.kotlinstudyexample.H5

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

/**
 * Created by hcw  on 2020/7/17
 * 类描述：
 * all rights reserved
 */

class JavaScriptMethods {

    private var mContext:Context? = null


    constructor(context: Context?){
        this.mContext = context
    }


    //4.2 之后，没有这个注解，h5 无法调用原生代码
    @JavascriptInterface
    fun showToast(json:String){
        Toast.makeText(mContext,json,Toast.LENGTH_LONG).show()
    }

}