package com.github.baby.owspace.model.util;

import com.github.baby.owspace.BuildConfig;
import com.github.baby.owspace.util.CustomLoggingInterceptor;

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

    private static CustomLoggingInterceptor createHttpLoggingInterceptor() {
        CustomLoggingInterceptor loggingInterceptor = new CustomLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? CustomLoggingInterceptor.Level.BODY : CustomLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }
}
