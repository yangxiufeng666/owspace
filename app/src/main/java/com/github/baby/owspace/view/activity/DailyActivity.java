package com.github.baby.owspace.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.baby.owspace.R;
import com.github.baby.owspace.app.OwspaceApplication;
import com.github.baby.owspace.di.components.DaggerDailyComponent;
import com.github.baby.owspace.di.modules.DailyModule;
import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.presenter.ArticalContract;
import com.github.baby.owspace.presenter.ArticalPresenter;
import com.github.baby.owspace.util.AppUtil;
import com.github.baby.owspace.view.adapter.DailyViewPagerAdapter;
import com.github.baby.owspace.view.widget.VerticalViewPager;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/3
 * owspace
 */
public class DailyActivity extends BaseActivity implements ArticalContract.View{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.view_pager)
    VerticalViewPager viewPager;
    @Inject
    ArticalPresenter presenter;
    private int page=1;
    private static final int MODE = 4;
    private boolean isLoading=true;
    private String deviceId;
    private DailyViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        ButterKnife.bind(this);
        initPresenter();
        initView();
        deviceId = AppUtil.getDeviceId(this);
        presenter.getListByPage(page,MODE,"0", deviceId,"0");
    }
    private void initPresenter(){
        DaggerDailyComponent.builder()
                .dailyModule(new DailyModule(this))
                .netComponent(OwspaceApplication.get(this).getNetComponent())
                .build()
                .inject(this);
    }
    private void initView() {
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        pagerAdapter = new DailyViewPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (pagerAdapter.getCount() <= position +2 && !isLoading){
                    Logger.e("page="+page+",getLastItemId="+pagerAdapter.getLastItemId());
                    presenter.getListByPage(page, 0, pagerAdapter.getLastItemId(),deviceId,pagerAdapter.getLastItemCreateTime());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        Toast.makeText(this,"没有更多数据了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateListUI(List<Item> itemList) {
        isLoading = false;
        pagerAdapter.setArtList(itemList);
        page++;
    }

    @Override
    public void showOnFailure() {
        if (pagerAdapter.getCount()==0){
            showNoData();
        }else{
            Toast.makeText(this,"加载数据失败，请检查您的网络",Toast.LENGTH_SHORT).show();
        }
    }
}
