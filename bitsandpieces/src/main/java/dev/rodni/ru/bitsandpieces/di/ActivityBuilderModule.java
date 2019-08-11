package dev.rodni.ru.bitsandpieces.di;

import android.content.Context;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dev.rodni.ru.bitsandpieces.di.auth.AuthModule;
import dev.rodni.ru.bitsandpieces.di.auth.AuthScope;
import dev.rodni.ru.bitsandpieces.di.auth.AuthViewModelsModule;
import dev.rodni.ru.bitsandpieces.di.main.MainFragmentBuilderModule;
import dev.rodni.ru.bitsandpieces.di.main.MainModule;
import dev.rodni.ru.bitsandpieces.di.main.MainScope;
import dev.rodni.ru.bitsandpieces.di.main.MainViewModelsModule;
import dev.rodni.ru.bitsandpieces.ui.auth.AuthActivity;
import dev.rodni.ru.bitsandpieces.ui.main.MainActivity;

@Module
public abstract class ActivityBuilderModule {
    //by this we talk that auth activity is a potential client
    @AuthScope
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelsModule.class,
                    AuthModule.class,
            }
    )
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuilderModule.class,
                    MainViewModelsModule.class,
                    MainModule.class,
            }
    )
    abstract MainActivity contributeMainActivity();
}
