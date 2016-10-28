package com.github.baby.owspace.di.components;

import com.github.baby.owspace.di.modules.ArtModule;
import com.github.baby.owspace.di.scopes.UserScope;
import com.github.baby.owspace.view.activity.ArtActivity;

import dagger.Component;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/22
 * owspace
 */
@UserScope
@Component(modules = ArtModule.class,dependencies = NetComponent.class)
public interface ArtComponent {
    void inject(ArtActivity artActivity);
}
