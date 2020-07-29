package com.hc.kotlinstudyexample.H5

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by hcw  on 2020/7/17
 * 类描述：
 * all rights reserved
 */

class JavaScriptMethods {

    private var mContext:Context? = null

    private var mWebView:WebView? = null;

    //懒加载 dialog
    private val mDialog:BottomUpDialog by lazy {
        BottomUpDialog(mContext!!)
    }


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
    fun getHotelDataTest(json:String){
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
                        //原生可以请求服务器，回传给 h5，实现 h5 动态更新数据
                        it.loadUrl("javascript:"+callback+"("+json.toString()+ ")")

                    }
                }
            }


        }
    }

    fun  showPhoneDialog(){
        mDialog.show()
    }


    /**
     * 获取酒店详情数据
     */
    public fun getHotelData(str: String) {
        try {
            //解析js callback方法
            val mJson = JSONObject(str)
            val callback = mJson.optString("callback") //解析js回调方法
            val json = JSONObject()
            json.put("hotel_name", "维多利亚大酒店")
            json.put("order_status", "已支付")
            json.put("orderId", "201612291809626")
            json.put("seller", "携程")
            json.put("expire_time", "2017年1月6日 23:00")
            json.put("price", "688.0")
            json.put("back_price", "128.0")
            json.put("pay_tpye", "支付宝支付")
            json.put("room_size", "3间房")
            json.put("room_count", "3")
            json.put("in_date", "2017年1月6日 12:00")
            json.put("out_date", "2017年1月8日 12:00")
            json.put("contact", "赵子龙先生")
            json.put("phone", "18888888888")
            json.put("server_phone", "0755-85699309")
            json.put("address", "深圳市宝安区兴东地铁站旁边")

            //调用js方法必须在主线程
//            webView.loadUrl("javascript:"+callback+"(" + json.toString() + ")");
          //  invokeJavaScript(callback, json.toString())

            mContext?.let {
                it.runOnUiThread {
                    mWebView?.let {
                        Log.i("HCTAG", "getHotelData: 调用 js ")
                      //  var json = JSONObject()
                     //   json.put("name","这是回调2")
                        //原生可以请求服务器，回传给 h5，实现 h5 动态更新数据
                        it.loadUrl("javascript:"+callback+"("+json.toString()+ ")")

                    }
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


    @JavascriptInterface
    fun  invokeMethod(method:String,params:Array<String>){
        Log.i("HCTAG", "invokeMethod: method:" + method + ",params:" + params.get(0)?.toString())
      //  mDialog.show()
        val tempJson = JSONObject(params.get(0).toString())
        var invokeMethod =   tempJson.optString("action") //解析js回调方法

        when(invokeMethod){
            in "getHotelData"->getHotelData(params?.get(0)?.toString())
            in "showCallPhoneDialog" -> showPhoneDialog()

        }


    }



}