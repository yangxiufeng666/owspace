package com.github.baby.owspace.di.components;

import com.github.baby.owspace.di.modules.SplashModule;
import com.github.baby.owspace.di.scopes.UserScope;
import com.github.baby.owspace.view.activity.SplashActivity;

import dagger.Component;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/25
 * owspace
 */
@UserScope
@Component(modules = SplashModule.class,dependencies = NetComponent.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
