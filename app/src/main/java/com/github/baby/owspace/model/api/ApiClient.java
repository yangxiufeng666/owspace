package com.github.baby.owspace.model.api;

import com.github.baby.owspace.model.util.EntityUtils;
import com.github.baby.owspace.model.util.HttpUtils;

import java.text.StringCharacterIterator;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/21
 * Owspace
 */
public final class ApiClient {
    private ApiClient(){}
//    public static final String API_BASE_URL = "http://static.owspace.com/";
    //converters 被添加的顺序将是它们被Retrofit尝试的顺序
//    public static final ApiService service = new Retrofit.Builder()
//            .baseUrl(API_BASE_URL)
//            .client(HttpUtils.client)
//            .addConverterFactory(StringConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(EntityUtils.gson))//
//            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//            .build()
//            .create(ApiService.class);
}
