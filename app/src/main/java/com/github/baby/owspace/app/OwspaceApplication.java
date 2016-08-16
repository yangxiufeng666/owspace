package com.github.baby.owspace.app;

import android.app.Application;

import com.github.baby.owspace.BuildConfig;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/21
 * Owspace
 */
public class OwspaceApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();
    }
    private void initLogger(){
        if (!BuildConfig.API_ENV){
            Logger.init("GithubOwspace")                 // default PRETTYLOGGER or use just init()
                    .methodCount(3)                 // default 2
                    .logLevel(LogLevel.FULL) ;       // default LogLevel.FULL
        }else{
            Logger.init("GithubOwspace")                 // default PRETTYLOGGER or use just init()
                    .methodCount(3)                 // default 2
                    .logLevel(LogLevel.NONE) ;       // default LogLevel.FULL
        }
    }
}
