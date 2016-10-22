package com.github.baby.owspace.presenter;


import com.github.baby.owspace.model.api.ApiClient;
import com.github.baby.owspace.model.entity.DetailEntity;
import com.github.baby.owspace.model.entity.Result;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/9
 * owspace
 */
public class DetailPresenter implements DetailContract.Presenter{
    private DetailContract.View view;
    @Inject
    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getDetail(String itemId) {
        ApiClient.service.getDetail("api","getPost",itemId,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result.Data<DetailEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showOnFailure();
                    }

                    @Override
                    public void onNext(Result.Data<DetailEntity> detailEntityData) {
                        view.updateListUI(detailEntityData.getDatas());
                    }
                });
    }
}
