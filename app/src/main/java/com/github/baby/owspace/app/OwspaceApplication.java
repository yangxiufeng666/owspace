package com.github.baby.owspace.app;

import android.app.Application;

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
        Logger.init("GithubOwspace")
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2);               // default 0
    }
}
