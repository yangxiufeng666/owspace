package com.github.baby.owspace.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.github.baby.owspace.R;
import com.github.baby.owspace.model.entity.Item;
import com.github.baby.owspace.presenter.MainContract;
import com.github.baby.owspace.presenter.MainPresenter;
import com.github.baby.owspace.util.AppUtil;
import com.github.baby.owspace.view.adapter.VerticalPagerAdapter;
import com.github.baby.owspace.view.fragment.LeftMenuFragment;
import com.github.baby.owspace.view.fragment.RightMenuFragment;
import com.github.baby.owspace.view.listener.SlideMenuOption;
import com.github.baby.owspace.view.widget.VerticalViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SlideMenuOption, MainContract.View {

    @Bind(R.id.view_pager)
    VerticalViewPager viewPager;
    private SlidingMenu slidingMenu;
    private LeftMenuFragment leftMenu;
    private RightMenuFragment rightMenu;

    private MainPresenter presenter;
    private VerticalPagerAdapter pagerAdapter;

    private int page = 1;
    private boolean isLoading = true;
    private long mLastClickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initMenu();
        initPage();
        loadData(1, 0, "0", "0");
    }

    private void initMenu() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setFadeEnabled(true);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.left_menu);
        leftMenu = new LeftMenuFragment();
        leftMenu.setSlideMenuOption(this);
        getSupportFragmentManager().beginTransaction().add(R.id.left_menu, leftMenu).commit();
        slidingMenu.setSecondaryMenu(R.layout.right_menu);
        rightMenu = new RightMenuFragment();
        rightMenu.setSlideMenuOption(this);
        getSupportFragmentManager().beginTransaction().add(R.id.right_menu, rightMenu).commit();
    }

    private void initPage() {
        pagerAdapter = new VerticalPagerAdapter(getSupportFragmentManager());
        presenter = new MainPresenter(this, this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (pagerAdapter.getCount() <= position + 2 && !isLoading) {
                    Logger.i("page=" + page + ",getLastItemId=" + pagerAdapter.getLastItemId());
                    loadData(page, 0, pagerAdapter.getLastItemId(), pagerAdapter.getLastItemCreateTime());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void loadData(int page, int mode, String pageId, String createTime) {
        isLoading = true;
        presenter.getListByPage(page, mode, pageId, AppUtil.getDeviceId(this), createTime);
    }

    @Override
    public void onBackPressed() {
        if (slidingMenu.isMenuShowing() || slidingMenu.isSecondaryMenuShowing()) {
            slidingMenu.showContent();
        } else {
            if (System.currentTimeMillis() - mLastClickTime <= 2000L) {
                super.onBackPressed();
            } else {
                mLastClickTime = System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public void showMenu(int flag) {
        if (flag == 1) {
            slidingMenu.showMenu();
        } else {
            slidingMenu.showSecondaryMenu();
        }
    }

    @Override
    public void hideMenu() {
        slidingMenu.showContent();
    }

    @OnClick({R.id.left_slide, R.id.right_slide})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_slide:
                slidingMenu.showMenu();
                leftMenu.startAnim();
                break;
            case R.id.right_slide:
                slidingMenu.showSecondaryMenu();
                rightMenu.startAnim();
                break;
        }
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
        Toast.makeText(this, "没有更多数据了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateListUI(List<Item> itemList) {
        isLoading = false;
        pagerAdapter.setDataList(itemList);
        page++;
    }

    @Override
    public void showOnFailure() {
        if (pagerAdapter.getCount() == 0) {
            showNoData();
        }
        Toast.makeText(this, "加载数据失败，请检查您的网络", Toast.LENGTH_SHORT).show();
    }
}
