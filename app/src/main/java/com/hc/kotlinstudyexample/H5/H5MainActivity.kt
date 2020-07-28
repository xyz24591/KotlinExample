package com.hc.kotlinstudyexample.H5

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.hc.kotlinstudyexample.R
import kotlinx.android.synthetic.main.activity_h5_main.*
import org.json.JSONObject

/**
 * Created by hcw  on 2020/7/27
 * 类描述：
 * all rights reserved
 */
class H5MainActivity :AppCompatActivity() {


    //懒加载
    private val mWebView : WebView  by lazy {
        web_view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h5_main)

        setWebView()

    }


    //lambda 表达式
    var setWebView = {

        //1、开启Kotlin 与 H5 通信开关
        mWebView.settings.javaScriptEnabled  = true
        //2、设置 2 个 client
        mWebView.webViewClient = MyWebViewClient()
        mWebView.webChromeClient = MyWebChromeClient()

        //h5 与 kotlin 通信
        mWebView.addJavascriptInterface(JavaScriptMethods(H5MainActivity@this,mWebView),"jsInterface")


        //3、加载网页
        //mWebView.loadUrl("https://www.baidu.com")
        mWebView.loadUrl("file:///android_asset/BridgeWebView/index.html") //本地模板
    }





    //通过 inner 来使用外部类的成员变量
   inner private class  MyWebViewClient : WebViewClient(){
        //页面加载完成调用
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            //页面加载完成，调用 js,通过 laodUrl
            var json = JSONObject()
            json.put("name","Kotlin")
            mWebView.loadUrl("javascript:showMessage(" + json.toString() + ")")

        }
    }

    private class  MyWebChromeClient: WebChromeClient(){
        //加载进度条
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
        }

    }
}