package com.rodnidev.dagger2example.component;

import com.rodnidev.dagger2example.interfaces.RandomUserApplicationScope;
import com.rodnidev.dagger2example.interfaces.RandomUsersApi;
import com.rodnidev.dagger2example.module.PicassoModule;
import com.rodnidev.dagger2example.module.RandomUsersModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by Hari on 23/11/17.
 */
@RandomUserApplicationScope
@Component(modules = {RandomUsersModule.class, PicassoModule.class})
public interface RandomUserComponent {

    RandomUsersApi getRandomUserService();

    Picasso getPicasso();
}
