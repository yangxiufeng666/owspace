package com.github.baby.owspace.presenter;

import android.content.Context;

import com.github.baby.owspace.model.api.ApiClient;
import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.model.entity.Result;
import com.github.baby.owspace.util.TimeUtil;

import java.util.List;

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
    private Context context;

    public MainPresenter(MainContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getListByPage(int page, int model, String pageId, String deviceId, String createTime) {
        ApiClient.service.getList("api", "getList", page, model, pageId, createTime, "android", "1.3.0", TimeUtil.getCurrentSeconds(), deviceId, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result.Data<List<Item>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
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
}
