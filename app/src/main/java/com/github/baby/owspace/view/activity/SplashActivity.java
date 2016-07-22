package com.github.baby.owspace.view.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.baby.owspace.R;
import com.github.baby.owspace.presenter.SplashContract;
import com.github.baby.owspace.presenter.SplashPresenter;
import com.github.baby.owspace.view.widget.FixedImageView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/22
 * owspace
 */
public class SplashActivity extends AppCompatActivity implements SplashContract.View{
    @Bind(R.id.splash_img)
    FixedImageView splashImg;

    SplashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SplashPresenter(this,this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_splash);
                ButterKnife.bind(SplashActivity.this);
                delaySplash();
            }
        },500);
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getSplash();
    }

    private void delaySplash(){
        AssetManager assetManager = this.getAssets();
        try {
            InputStream in = assetManager.open("welcome_default.jpg");
            splashImg.setBackgroundDrawable(InputStream2Drawable(in));
            animWelcomeImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Drawable InputStream2Drawable(InputStream is) {
        Drawable drawable = BitmapDrawable.createFromStream(is,"splashImg");
        return drawable;
    }
    private void animWelcomeImage()
    {
        ObjectAnimator animator = ObjectAnimator.ofFloat(splashImg,"translationX",-100F);
        animator.setDuration(1500L).start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
