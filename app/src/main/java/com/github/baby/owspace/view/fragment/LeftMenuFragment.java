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
import android.widget.LinearLayout;
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
public class LeftMenuFragment extends Fragment {
    @Bind(R.id.right_slide_close)
    ImageView rightSlideClose;
    @Bind(R.id.search)
    ImageView search;
    @Bind(R.id.home_page_tv)
    TextView homePageTv;
    @Bind(R.id.words_tv)
    TextView wordsTv;
    @Bind(R.id.voice_tv)
    TextView voiceTv;
    @Bind(R.id.video_tv)
    TextView videoTv;
    @Bind(R.id.calendar_tv)
    TextView calendarTv;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;

    private SlideMenuOption slideMenuOption;
    private List<View> mViewList = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left_menu, container, false);
        ButterKnife.bind(this, view);
        loadView();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void setSlideMenuOption(SlideMenuOption slideMenuOption) {
        this.slideMenuOption = slideMenuOption;
    }

    private void loadView() {
        mViewList.add(homePageTv);
        mViewList.add(wordsTv);
        mViewList.add(voiceTv);
        mViewList.add(videoTv);
        mViewList.add(calendarTv);
    }

    @OnClick({R.id.right_slide_close, R.id.search, R.id.home_page_tv, R.id.words_tv, R.id.voice_tv, R.id.video_tv, R.id.calendar_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right_slide_close:
                slideMenuOption.hideMenu();
                break;
            case R.id.search:
                break;
            case R.id.home_page_tv:
                break;
            case R.id.words_tv:
                break;
            case R.id.voice_tv:
                break;
            case R.id.video_tv:
                break;
            case R.id.calendar_tv:
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
            localTranslateAnimation = new TranslateAnimation(j * -35, 0.0F, 0.0F, 0.0F);
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
