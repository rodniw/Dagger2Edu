package dev.rodni.ru.bitsandpieces.di;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dev.rodni.ru.bitsandpieces.viewmodels.ViewModelProviderFactory;

@Module
public abstract class ViewModelFactoryModule {

    //binds is all the same as static provides if we do not make smth inside method body
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
}
