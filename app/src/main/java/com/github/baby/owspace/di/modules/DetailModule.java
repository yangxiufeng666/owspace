package com.github.baby.owspace.di.modules;

import com.github.baby.owspace.presenter.DetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/22
 * owspace
 */
@Module
public class DetailModule {
    private DetailContract.View mView;

    public DetailModule(DetailContract.View mView) {
        this.mView = mView;
    }
    @Provides
    public DetailContract.View provideView(){
        return mView;
    }
}
