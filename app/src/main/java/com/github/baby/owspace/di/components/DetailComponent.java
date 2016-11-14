package com.github.baby.owspace.di.components;

import com.github.baby.owspace.di.modules.DetailModule;
import com.github.baby.owspace.di.scopes.UserScope;
import com.github.baby.owspace.view.activity.AudioDetailActivity;
import com.github.baby.owspace.view.activity.DetailActivity;
import com.github.baby.owspace.view.activity.VideoDetailActivity;

import dagger.Component;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/22
 * owspace
 */
@UserScope
@Component(modules = DetailModule.class,dependencies = NetComponent.class)
public interface DetailComponent {
    void inject(DetailActivity activity);
    void inject(VideoDetailActivity activity);
    void inject(AudioDetailActivity activity);
}
