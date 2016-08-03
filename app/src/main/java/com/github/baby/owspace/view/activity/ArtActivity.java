package com.github.baby.owspace.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.baby.owspace.R;
import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.presenter.ArticalPresenter;
import com.github.baby.owspace.presenter.ListBaseContract;
import com.github.baby.owspace.util.AppUtil;
import com.github.baby.owspace.view.adapter.ArtRecycleViewAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/3
 * owspace
 */
public class ArtActivity extends AppCompatActivity implements ListBaseContract.ListBaseView {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.toolBar)
    Toolbar toolbar;
    @Bind(R.id.recycleView)
    RecyclerView recycleView;

    private ArticalPresenter presenter;
    private ArtRecycleViewAdapter recycleViewAdapter;
    private int page=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recycleViewAdapter = new ArtRecycleViewAdapter(this);
        recycleView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recycleView.setAdapter(recycleViewAdapter);
        presenter = new ArticalPresenter(this, this);
        int mode = getIntent().getIntExtra("mode",1);
        String tt = getIntent().getStringExtra("title");
        title.setText(tt);
        loadData(page, mode, "0", "0");
    }

    private void loadData(int page, int mode, String pageId, String createTime) {
        presenter.getListByPage(page, mode, pageId, AppUtil.getDeviceId(this), createTime);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showNoMore() {

    }

    @Override
    public void updateListUI(List<Item> itemList) {
        recycleViewAdapter.setArtList(itemList);
    }

    @Override
    public void showOnFailure() {

    }
}
