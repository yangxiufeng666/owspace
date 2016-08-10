package com.github.baby.owspace.presenter;


import com.github.baby.owspace.model.api.ApiClient;
import com.github.baby.owspace.model.entity.DetailEntity;
import com.github.baby.owspace.model.entity.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/9
 * owspace
 */
public class DetailPresenter implements DetailContract.Presenter{
    private DetailContract.View view;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getDetail(String itemId) {
        Call<Result.Data<DetailEntity>> call = ApiClient.service.getDetail("api","getPost",itemId,1);
        call.enqueue(new Callback<Result.Data<DetailEntity>>() {
            @Override
            public void onResponse(Call<Result.Data<DetailEntity>> call, Response<Result.Data<DetailEntity>> response) {
                view.updateListUI(response.body().getDatas());
            }

            @Override
            public void onFailure(Call<Result.Data<DetailEntity>> call, Throwable t) {
                view.showOnFailure();
            }
        });
    }
}
