package com.rodnidev.dagger2example.application;

import android.app.Activity;
import android.app.Application;

import com.rodnidev.dagger2example.component.DaggerRandomUserComponent;
import com.rodnidev.dagger2example.component.RandomUserComponent;
import com.rodnidev.dagger2example.module.ContextModule;

import timber.log.Timber;

/**
 * Created by Hari on 20/12/17.
 */

public class RandomUserApplication extends Application {

    //add application name in Manifest file
    private RandomUserComponent randomUserApplicationComponent;

    public static RandomUserApplication get(Activity activity){
        return (RandomUserApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        randomUserApplicationComponent = DaggerRandomUserComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public RandomUserComponent getRandomUserApplicationComponent(){
        return randomUserApplicationComponent;
    }
}
