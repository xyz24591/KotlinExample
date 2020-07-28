package com.hc.kotlinstudyexample.H5

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.net.URL

/**
 * Created by hcw  on 2020/7/17
 * 类描述：
 * all rights reserved
 */

class JavaScriptMethods {

    private var mContext:Context? = null

    private var mWebView:WebView? = null;


    constructor(context: Context?,webView: WebView?){
        this.mContext = context
        this.mWebView = webView
    }


    //4.2 之后，没有这个注解，h5 无法调用原生代码
    @JavascriptInterface
    fun showToast(json:String){
        //Toast.makeText(mContext,json,Toast.LENGTH_LONG).show()
        mContext?.let {
            it.toast(json)
        }
    }


    @JavascriptInterface
    fun getHotelData(json:String){
        Log.i("HCTAG", "getHotelData: " + json)
        var jsJson = JSONObject(json)
        var callback = jsJson.optString("callback")

        //异步操作
        doAsync {
            //操作 js 方法必须主线程
          //  var url = URL("https:www.baidu.com")
          //  var  result = url.readText()
            mContext?.let {
                it.runOnUiThread {
                    mWebView?.let {
                        Log.i("HCTAG", "getHotelData: 调用 js ")
                        var json = JSONObject()
                        json.put("name","这是回调2")
                        it.loadUrl("javascript:"+callback+"("+json.toString()+ ")")

                    }
                }
            }


        }
    }




}