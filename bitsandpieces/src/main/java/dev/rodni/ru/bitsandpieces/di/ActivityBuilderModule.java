package dev.rodni.ru.bitsandpieces.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dev.rodni.ru.bitsandpieces.ui.auth.AuthActivity;

@Module
public abstract class ActivityBuilderModule {
    //by this we talk that auth activity is a potential client
    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();
}
