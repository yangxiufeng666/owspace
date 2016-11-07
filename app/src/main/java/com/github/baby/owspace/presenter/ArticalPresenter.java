package com.github.baby.owspace.presenter;

import com.github.baby.owspace.model.api.ApiService;
import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.model.entity.Result;
import com.github.baby.owspace.util.TimeUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/3
 * owspace
 */
public class ArticalPresenter implements ArticalContract.Presenter{

    private ArticalContract.View view;
    private ApiService apiService;
    @Inject
    public ArticalPresenter(ArticalContract.View view,ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void getListByPage(int page, int model, String pageId, String deviceId, String createTime) {
        apiService.getList("api","getList",page,model,pageId,createTime,"android","1.3.0", TimeUtil.getCurrentSeconds(), deviceId,1)
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
                       if(size>0){
                           view.updateListUI(listData.getDatas());
                       }else {
                           view.showNoMore();
                       }
                   }
               });
    }
}
