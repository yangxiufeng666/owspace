package com.github.baby.owspace.di.components;

import com.github.baby.owspace.di.modules.DailyModule;
import com.github.baby.owspace.di.scopes.UserScope;
import com.github.baby.owspace.view.activity.DailyActivity;

import dagger.Component;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/22
 * owspace
 */
@UserScope
@Component(modules = DailyModule.class,dependencies = NetComponent.class)
public interface DailyComponent {
    void inject(DailyActivity activity);
}
