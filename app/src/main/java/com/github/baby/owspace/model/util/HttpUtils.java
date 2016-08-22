package com.github.baby.owspace.model.util;

import com.github.baby.owspace.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/21
 * Owspace
 */
public class HttpUtils {
    private HttpUtils() {}
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .addInterceptor(createHttpLoggingInterceptor())
            .build();

    private static HttpLoggingInterceptor createHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }
}
