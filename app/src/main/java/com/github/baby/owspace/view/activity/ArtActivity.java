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
import com.github.baby.owspace.view.widget.DividerItemDecoration;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
    XRecyclerView recycleView;

    private ArticalPresenter presenter;
    private ArtRecycleViewAdapter recycleViewAdapter;
    private int page=1;
    private int mode=1;
    private boolean isRefresh;
    private String deviceId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout);
        ButterKnife.bind(this);
        mode = getIntent().getIntExtra("mode",1);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        String tt = getIntent().getStringExtra("title");
        title.setText(tt);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recycleViewAdapter = new ArtRecycleViewAdapter(this);
        recycleView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recycleView.addItemDecoration(new DividerItemDecoration(this));
        recycleView.setAdapter(recycleViewAdapter);
        recycleView.setLoadingMoreEnabled(true);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        recycleView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setArrowImageView(R.drawable.iconfont_downgrey);
        presenter = new ArticalPresenter(this, this);
        recycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                isRefresh = true;
                loadData(page, mode, "0",deviceId, "0");
            }

            @Override
            public void onLoadMore() {
                loadData(page, mode, recycleViewAdapter.getLastItemId(),deviceId, recycleViewAdapter.getLastItemCreateTime());
            }
        });
        deviceId =  AppUtil.getDeviceId(this);
        recycleView.setRefreshing(true);
    }

    private void loadData(int page, int mode, String pageId, String deviceId,String createTime) {
        presenter.getListByPage(page, mode, pageId, deviceId, createTime);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showNoData() {
        recycleView.refreshComplete();
        recycleView.loadMoreComplete();
    }

    @Override
    public void showNoMore() {
        recycleView.setIsnomore(true);
    }

    @Override
    public void updateListUI(List<Item> itemList) {
        page++;
        recycleView.refreshComplete();
        recycleView.loadMoreComplete();
        recycleView.setIsnomore(false);
        if (isRefresh){
            isRefresh = false;
            recycleViewAdapter.replaceAllData(itemList);
        }else {
            recycleViewAdapter.setArtList(itemList);
        }

    }

    @Override
    public void showOnFailure() {
        recycleView.refreshComplete();
        recycleView.loadMoreComplete();
        recycleView.setIsnomore(false);
    }
}
