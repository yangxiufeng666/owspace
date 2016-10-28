package com.github.baby.owspace.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/22
 * owspace
 */
@Module
public class ApplicationModule {
    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context providerContext(){
        return context;
    }

}
