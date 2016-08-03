package com.github.baby.owspace.presenter;

import android.content.Context;

import com.github.baby.owspace.model.api.ApiClient;
import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.model.entity.Result;
import com.github.baby.owspace.util.TimeUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/3
 * owspace
 */
public class ArticalPresenter implements ListBaseContract.ListBasePresenter{

    private ListBaseContract.ListBaseView view;
    private Context context;

    public ArticalPresenter(ListBaseContract.ListBaseView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getListByPage(int page, int model, String pageId, String deviceId, String createTime) {
        Call<Result.Data<List<Item>>> call = ApiClient.service.getList("api","getList",page,model,pageId,createTime,"android","1.3.0", TimeUtil.getCurrentSeconds(), deviceId,1);
        call.enqueue(new Callback<Result.Data<List<Item>>>() {
            @Override
            public void onResponse(Call<Result.Data<List<Item>>> call, Response<Result.Data<List<Item>>> response) {
                Result.Data<List<Item>> listData = response.body();
                int size = listData.getDatas().size();
                if(size>0){
                    view.updateListUI(listData.getDatas());
                }else {
                    view.showNoMore();
                }
            }

            @Override
            public void onFailure(Call<Result.Data<List<Item>>> call, Throwable t) {
                view.showOnFailure();
            }
        });
    }
}
