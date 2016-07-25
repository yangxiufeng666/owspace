package com.github.baby.owspace.presenter;


import android.content.Context;

import com.bumptech.glide.Glide;
import com.github.baby.owspace.model.api.ApiClient;
import com.github.baby.owspace.model.entity.SplashEntity;
import com.github.baby.owspace.model.util.HttpUtils;
import com.github.baby.owspace.util.AppUtil;
import com.github.baby.owspace.util.NetUtil;
import com.github.baby.owspace.util.OkHttpImageDownloader;
import com.github.baby.owspace.util.TimeUtil;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/22
 * owspace
 */
public class SplashPresenter implements SplashContract.Presenter{
    private SplashContract.View view;
    private Context context;

    public SplashPresenter(SplashContract.View view,Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getSplash() {
        //String client, String version, Long time,String imei
        String client = "android";
        String version = "1.3.0";
        Long time = TimeUtil.getCurrentSeconds();
        String deviceId = AppUtil.getDeviceId(context);
        Call<SplashEntity> call = ApiClient.service.getSplash(client,version,time,deviceId);
        call.enqueue(new Callback<SplashEntity>() {
            @Override
            public void onResponse(Call<SplashEntity> call, Response<SplashEntity> response) {
                SplashEntity splashEntity = response.body();
                if (NetUtil.isWifi(context)){
                    if (splashEntity != null){
                        List<String> imgs = splashEntity.getImages();
                        for (String url:imgs) {
                            OkHttpImageDownloader.download(url);
                        }
                    }
                }else{
                    Logger.i("不是WIFI环境");
                }
            }
            @Override
            public void onFailure(Call<SplashEntity> call, Throwable t) {

            }
        });
    }
}
