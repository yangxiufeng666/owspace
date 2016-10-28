package com.github.baby.owspace.di.modules;

import com.github.baby.owspace.presenter.SplashContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/22
 * owspace
 */
@Module
public class SplashModule {
    private SplashContract.View view;

    public SplashModule(SplashContract.View view) {
        this.view = view;
    }
    @Provides
    public SplashContract.View provideView(){
        return view;
    }
}
