package com.rodnidev.dagger2example.module;

import android.content.Context;

import com.rodnidev.dagger2example.interfaces.ApplicationContext;
import com.rodnidev.dagger2example.interfaces.RandomUserApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hari on 23/11/17.
 */
@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @ApplicationContext
    @RandomUserApplicationScope
    @Provides
    public Context context(){ return context.getApplicationContext(); }
}
