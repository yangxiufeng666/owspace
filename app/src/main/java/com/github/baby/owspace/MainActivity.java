package com.github.baby.owspace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.baby.owspace.model.api.ApiClient;
import com.github.baby.owspace.model.entity.Detail;
import com.github.baby.owspace.model.entity.Result;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Result.Data<Detail>> detail = ApiClient.service.getDetail("api","getPost",292296,1);
                detail.enqueue(new Callback<Result.Data<Detail>>() {
                    @Override
                    public void onResponse(Call<Result.Data<Detail>> call, Response<Result.Data<Detail>> response) {
                        System.out.println("aa:"+response.body().getDatas().getAuthor());
                    }

                    @Override
                    public void onFailure(Call<Result.Data<Detail>> call, Throwable t) {

                    }
                });
            }
        });
    }
}
