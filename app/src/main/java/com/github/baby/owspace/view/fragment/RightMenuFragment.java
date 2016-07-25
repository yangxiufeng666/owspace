package com.github.baby.owspace.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.baby.owspace.R;
import com.github.baby.owspace.view.listener.SlideMenuOption;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/22
 * owspace
 */
public class RightMenuFragment extends Fragment {
    @Bind(R.id.right_slide_close)
    ImageView rightSlideClose;
    @Bind(R.id.setting)
    ImageView setting;
    @Bind(R.id.avater_iv)
    ImageView avaterIv;
    @Bind(R.id.name_tv)
    TextView nameTv;
    @Bind(R.id.notification_tv)
    TextView notificationTv;
    @Bind(R.id.favorites_tv)
    TextView favoritesTv;
    @Bind(R.id.download_tv)
    TextView downloadTv;
    @Bind(R.id.note_tv)
    TextView noteTv;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;

    private SlideMenuOption slideMenuOption;
    private List<View> mViewList = new ArrayList();

    public void setSlideMenuOption(SlideMenuOption slideMenuOption) {
        this.slideMenuOption = slideMenuOption;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_menu, container, false);
        ButterKnife.bind(this, view);
        loadView();
        return view;
    }
    private void loadView() {
        mViewList.add(notificationTv);
        mViewList.add(favoritesTv);
        mViewList.add(downloadTv);
        mViewList.add(noteTv);
    }
    @Override
    public void onResume() {
        super.onResume();
        Logger.e("RightMenuFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.e("RightMenuFragment onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.right_slide_close, R.id.setting, R.id.notification_tv, R.id.favorites_tv, R.id.download_tv, R.id.note_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right_slide_close:
                slideMenuOption.hideMenu();
                break;
            case R.id.setting:
                break;
            case R.id.notification_tv:
                break;
            case R.id.favorites_tv:
                break;
            case R.id.download_tv:
                break;
            case R.id.note_tv:
                break;
        }
    }
    public void startAnim() {
        startIconAnim(titleBar);
        startColumnAnim();
    }

    private void startColumnAnim() {
        int i = this.mViewList.size();
        int j = 0;
        TranslateAnimation localTranslateAnimation = new TranslateAnimation(j * -35, 0.0F, 0.0F, 0.0F);
        localTranslateAnimation.setDuration(700L);
        while (true) {
            View localView = this.mViewList.get(j);
            localView.startAnimation(localTranslateAnimation);
            j++;
            if (j >= i)
                break;
            localTranslateAnimation = new TranslateAnimation(j * 35,0.0F, 0.0F, 0.0F);
            localTranslateAnimation.setDuration(700L);
        }
    }

    private void startIconAnim(View paramView) {
        ScaleAnimation localScaleAnimation = new ScaleAnimation(0.1F, 1.0F, 0.1F, 1.0F, this.titleBar.getWidth() / 2, this.titleBar.getHeight() / 2);
        localScaleAnimation.setDuration(1000L);
        paramView.startAnimation(localScaleAnimation);
        float f1 = titleBar.getWidth() / 2;
        float f2 = titleBar.getHeight() / 2;
        localScaleAnimation = new ScaleAnimation(1.0F, 0.5F, 1.0F, 0.5F, f1, f2);
        localScaleAnimation.setInterpolator(new BounceInterpolator());
    }
}
