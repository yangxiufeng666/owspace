package com.github.baby.owspace.util;


import com.github.baby.owspace.model.util.HttpUtils;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Request;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/25
 * owspace
 */
public class OkHttpImageDownloader {
    public static void download(String url){
        final Request request = new Request.Builder().url(url).build();
        HttpUtils.client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Logger.d(e);
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Logger.d("dowload OK");

            }
        });
    }
}
