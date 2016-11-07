package com.github.baby.owspace.presenter;

import com.github.baby.owspace.model.api.ApiService;
import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.model.entity.Result;
import com.github.baby.owspace.util.TimeUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/2
 * owspace
 */
public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private ApiService apiService;

    @Inject
    public MainPresenter(MainContract.View view,ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
        Logger.d("apppp:"+apiService);
    }

    @Override
    public void getListByPage(int page, int model, String pageId, String deviceId, String createTime) {
        apiService.getList("api", "getList", page, model, pageId, createTime, "android", "1.3.0", TimeUtil.getCurrentSeconds(), deviceId, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result.Data<List<Item>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.showOnFailure();
                    }

                    @Override
                    public void onNext(Result.Data<List<Item>> listData) {
                        int size = listData.getDatas().size();
                        if (size > 0) {
                            view.updateListUI(listData.getDatas());
                        } else {
                            view.showNoMore();
                        }
                    }
                });
    }

    @Override
    public void getRecommend(String deviceId) {
        String key = TimeUtil.getDate("yyyyMMdd");
        Logger.e("getRecommend key:"+key);
        apiService.getRecommend("home","Api","getLunar","android",deviceId,deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("onError:");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        String key = TimeUtil.getDate("yyyyMMdd");
                        try {
                            JsonParser jsonParser = new JsonParser();
                            JsonElement jel = jsonParser.parse(s);
                            JsonObject jsonObject = jel.getAsJsonObject();
                            jsonObject = jsonObject.getAsJsonObject("datas");
                            jsonObject = jsonObject.getAsJsonObject(key);
                            view.showLunar(jsonObject.get("thumbnail").getAsString());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
}
