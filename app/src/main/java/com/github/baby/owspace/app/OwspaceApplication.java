package com.github.baby.owspace.app;

import android.app.Application;
import android.content.Context;

import com.github.baby.owspace.BuildConfig;
import com.github.baby.owspace.R;
import com.github.baby.owspace.di.components.DaggerNetComponent;
import com.github.baby.owspace.di.components.NetComponent;
import com.github.baby.owspace.di.modules.NetModule;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/21
 * Owspace
 */
public class OwspaceApplication extends Application{

    private static OwspaceApplication instance;

    public static OwspaceApplication get(Context context){
        return (OwspaceApplication)context.getApplicationContext();
    }

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLogger();
        initNet();
        initDatabase();
        initTypeFace();
    }
    private void initTypeFace() {
        CalligraphyConfig calligraphyConfig =new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/PMingLiU.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
        CalligraphyConfig.initDefault(calligraphyConfig);
    }

    private void initLogger(){
        LogLevel logLevel;
        if (!BuildConfig.API_ENV){
           logLevel = LogLevel.FULL;
        }else{
            logLevel = LogLevel.NONE;
        }
        Logger.init("GithubOwspace")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .logLevel(logLevel) ;       // default LogLevel.FULL
    }
    private void initNet(){
        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();
    }
    private void initDatabase(){

    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

    public static OwspaceApplication getInstance() {
        return instance;
    }
}
