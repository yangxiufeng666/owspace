package com.github.baby.owspace.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.baby.owspace.di.components.ApplicationComponent;
import com.github.baby.owspace.di.components.NetComponent;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/27
 * owspace
 */

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract void setUpNetComponent(NetComponent appComponent);
}
