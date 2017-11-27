package com.github.baby.owspace.view.fragment;

import android.content.Intent;
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
import com.github.baby.owspace.model.entity.Event;
import com.github.baby.owspace.util.tool.RxBus;
import com.github.baby.owspace.view.activity.ArtActivity;
import com.github.baby.owspace.view.activity.DailyActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/22
 * owspace
 */
public class LeftMenuFragment extends Fragment {
    @BindView(R.id.right_slide_close)
    ImageView rightSlideClose;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.home_page_tv)
    TextView homePageTv;
    @BindView(R.id.words_tv)
    TextView wordsTv;
    @BindView(R.id.voice_tv)
    TextView voiceTv;
    @BindView(R.id.video_tv)
    TextView videoTv;
    @BindView(R.id.calendar_tv)
    TextView calendarTv;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;

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
        Intent intent;
        switch (view.getId()) {
            case R.id.right_slide_close:
                RxBus.getInstance().postEvent(new Event(1000,"closeMenu"));
                break;
            case R.id.search:
                break;
            case R.id.home_page_tv:
                RxBus.getInstance().postEvent(new Event(1000,"closeMenu"));
                break;
            case R.id.words_tv:
                intent = new Intent(getActivity(), ArtActivity.class);
                intent.putExtra("mode",1);
                intent.putExtra("title","文  字");
                startActivity(intent);
                break;
            case R.id.voice_tv:
                intent = new Intent(getActivity(), ArtActivity.class);
                intent.putExtra("mode",3);
                intent.putExtra("title","声  音");
                startActivity(intent);
                break;
            case R.id.video_tv:
                intent = new Intent(getActivity(), ArtActivity.class);
                intent.putExtra("mode",2);
                intent.putExtra("title","影  像");
                startActivity(intent);
                break;
            case R.id.calendar_tv:
                intent = new Intent(getActivity(), DailyActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void startAnim() {
        startIconAnim(search);
        startIconAnim(rightSlideClose);
        startColumnAnim();
    }

    private void startColumnAnim() {
        TranslateAnimation localTranslateAnimation = new TranslateAnimation(0F, 0.0F, 0.0F, 0.0F);
        localTranslateAnimation.setDuration(700L);
        for (int j=0;j<mViewList.size();j++){
            View localView = this.mViewList.get(j);
            localView.startAnimation(localTranslateAnimation);
            localTranslateAnimation = new TranslateAnimation(j * -35,0.0F, 0.0F, 0.0F);
            localTranslateAnimation.setDuration(700L);
        }
    }

    private void startIconAnim(View paramView) {
        ScaleAnimation localScaleAnimation = new ScaleAnimation(0.1F, 1.0F, 0.1F, 1.0F, paramView.getWidth() / 2, paramView.getHeight() / 2);
        localScaleAnimation.setDuration(1000L);
        paramView.startAnimation(localScaleAnimation);
        float f1 = paramView.getWidth() / 2;
        float f2 = paramView.getHeight() / 2;
        localScaleAnimation = new ScaleAnimation(1.0F, 0.5F, 1.0F, 0.5F, f1, f2);
        localScaleAnimation.setInterpolator(new BounceInterpolator());
    }
}
