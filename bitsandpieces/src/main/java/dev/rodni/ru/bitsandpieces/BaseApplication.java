package dev.rodni.ru.bitsandpieces;

import android.content.Context;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import dev.rodni.ru.bitsandpieces.di.DaggerAppComponent;

public class BaseApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
