package dev.rodni.ru.bitsandpieces.di.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dev.rodni.ru.bitsandpieces.ui.main.posts.PostsFragment;
import dev.rodni.ru.bitsandpieces.ui.main.profile.ProfileFragment;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment contributePostsFragment();
}
