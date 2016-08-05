package com.github.baby.owspace.app;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import net.danlew.android.joda.JodaTimeAndroid;

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
        JodaTimeAndroid.init(this);
    }
    private void initLogger(){
        Logger.init("GithubOwspace")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .logLevel(LogLevel.FULL) ;       // default LogLevel.FULL
    }
}
