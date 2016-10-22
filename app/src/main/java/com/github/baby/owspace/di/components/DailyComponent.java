package com.github.baby.owspace.di.components;

import com.github.baby.owspace.di.modules.DailyModule;
import com.github.baby.owspace.view.activity.DailyActivity;

import dagger.Component;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/22
 * owspace
 */
@Component(modules = DailyModule.class)
public interface DailyComponent {
    void inject(DailyActivity activity);
}
