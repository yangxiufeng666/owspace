package com.github.baby.owspace.presenter;


import com.github.baby.owspace.model.api.ApiService;
import com.github.baby.owspace.model.entity.DetailEntity;
import com.github.baby.owspace.model.entity.Result;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/9
 * owspace
 */
public class DetailPresenter implements DetailContract.Presenter {
    private DetailContract.View view;
    private ApiService apiService;

    @Inject
    public DetailPresenter(DetailContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void getDetail(String itemId) {
        apiService.getDetail("api", "getPost", itemId, 1)
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
