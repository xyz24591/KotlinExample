package com.hc.kotlinstudyexample.https

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hc.kotlinstudyexample.R
import com.hc.kotlinstudyexample.databinding.ActivityH5MainBinding
import com.hc.kotlinstudyexample.databinding.ActivityHttpsMainBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.*

/**
 * Created by hcw  on 2020/8/3
 * 类描述：
 * all rights reserved
 */
class ActivityHttpsMain : AppCompatActivity() {

    private lateinit var binding:ActivityHttpsMainBinding
    val mBtnPay by lazy {
        binding.btnHttps
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHttpsMainBinding.inflate(layoutInflater)

        mBtnPay.onClick {
            //自线程请求接口，使用拉姆达\
            sendHttps()
        }

    }

    var sendHttps = {
        doAsync {

            //使用 Https
            //1、创建 ssl 上下文对象设置信任管理器
            val sslContext = SSLContext.getInstance("TLS")  //Tomcat 使用的证书使用的协议
            //拿到信任管理器对象
            val tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            //2、初始化工厂，设置 keystore
            val ks = KeyStore.getInstance(KeyStore.getDefaultType())
            //3、使用自己的证书(先清空)
            ks.load(null)
            val cf = CertificateFactory.getInstance("X.509") //固定的协议
            val cert = cf.generateCertificate(assets.open("key2020.cer"))
            ks.setCertificateEntry("key2020",cert)
            tmf.init(ks)
            val tm = tmf.trustManagers
            sslContext.init(null,tm,null)

            val url = URL("https://192.168.31.168:8443/alipay.json")
           // val url = URL("http://192.168.31.168:8080/alipay.json")
            var connection:HttpsURLConnection = url.openConnection() as HttpsURLConnection
           // var connection:HttpURLConnection = url.openConnection() as HttpURLConnection
            //4、使用信任管理器
            connection.sslSocketFactory = sslContext.socketFactory
            //5、设置主机名校验
            connection.hostnameVerifier  = MyHostNameVerifier()

            //获取服务器返回的流
            val ins   = connection.inputStream
            //转换成字符串
            var bos = ByteArrayOutputStream()
            var buffer = ByteArray(1024)
            var len = 0
            len = ins.read(buffer)
            while( len != -1){
                bos.write(buffer,0,len)
                len = ins.read(buffer)
            }

            val result = bos.toString()

            Log.i("HCTAG", ": 读取 https:" + result)

        }
    }

    private class MyHostNameVerifier:HostnameVerifier{
        //主机名校验
        override fun verify(p0: String?, p1: SSLSession?): Boolean {
           // TODO("Not yet implemented") //判断是否是公司的
            return  true
        }

    }
}