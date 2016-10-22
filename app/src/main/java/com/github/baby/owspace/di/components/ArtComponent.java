package com.github.baby.owspace.di.components;

import com.github.baby.owspace.di.modules.ArtModule;
import com.github.baby.owspace.view.activity.ArtActivity;

import dagger.Component;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/22
 * owspace
 */
@Component(modules = ArtModule.class)
public interface ArtComponent {
    void inject(ArtActivity artActivity);
}
