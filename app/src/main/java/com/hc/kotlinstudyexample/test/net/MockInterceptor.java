package com.hc.kotlinstudyexample.test.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by hcw  on 2020/12/12
 * 类描述：
 * all rights reserved
 */
public class MockInterceptor implements Interceptor {

    private final String responseString; //你要模拟返回的数据

    public MockInterceptor(String responseString) {
        this.responseString = responseString;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Response response = new Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();
        return response;
    }
}