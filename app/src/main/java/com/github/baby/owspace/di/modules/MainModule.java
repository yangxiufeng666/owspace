package com.github.baby.owspace.di.modules;

import com.github.baby.owspace.presenter.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/22
 * owspace
 */
@Module
public class MainModule {
    private final MainContract.View mView;

    public MainModule(MainContract.View mView) {
        this.mView = mView;
    }
    @Provides
    public MainContract.View provideMainView(){
        return mView;
    }
}
