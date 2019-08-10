package dev.rodni.ru.bitsandpieces.di.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dev.rodni.ru.bitsandpieces.ui.main.profile.ProfileFragment;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();
}
