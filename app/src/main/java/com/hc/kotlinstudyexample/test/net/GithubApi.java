package com.hc.kotlinstudyexample.test.net;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hcw  on 2020/12/12
 * 类描述：
 * all rights reserved
 */
public interface GithubApi {

    String BASE_URL = "https://api.github.com/";

    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);
}