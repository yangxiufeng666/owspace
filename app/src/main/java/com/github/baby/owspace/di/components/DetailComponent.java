package com.github.baby.owspace.di.components;

import com.github.baby.owspace.di.modules.DetailModule;
import com.github.baby.owspace.view.activity.DetailActivity;

import dagger.Component;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/22
 * owspace
 */
@Component(modules = DetailModule.class)
public interface DetailComponent {
    void inject(DetailActivity activity);
}
