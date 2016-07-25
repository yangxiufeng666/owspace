package com.github.baby.owspace.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.github.baby.owspace.R;
import com.github.baby.owspace.view.fragment.LeftMenuFragment;
import com.github.baby.owspace.view.fragment.RightMenuFragment;
import com.github.baby.owspace.view.listener.SlideMenuOption;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SlideMenuOption {

    @Bind(R.id.left_slide)
    ImageView leftSlide;
    @Bind(R.id.right_slide)
    ImageView rightSlide;

    private SlidingMenu slidingMenu;
    private LeftMenuFragment leftMenu;
    private RightMenuFragment rightMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initMenu();

    }

    private void initMenu() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);
//        slidingMenu.setBehindOffset(158);
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
        slidingMenu.setOnOpenListener(new SlidingMenu.OnOpenListener() {
            @Override
            public void onOpen() {

            }
        });
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
}
