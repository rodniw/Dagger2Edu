package com.rodnidev.dagger2example.MainActivityFeature;

import com.rodnidev.dagger2example.MainActivity;
import com.rodnidev.dagger2example.component.RandomUserComponent;

import dagger.Component;

/**
 * Created by Hari on 20/12/17.
 */
@Component(modules = MainActivityModule.class, dependencies = RandomUserComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);

}
