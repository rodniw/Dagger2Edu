package dev.rodni.ru.bitsandpieces.di.auth;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dev.rodni.ru.bitsandpieces.di.ViewModelKey;
import dev.rodni.ru.bitsandpieces.ui.auth.AuthViewModel;

@Module
public abstract class AuthViewModelsModule {

    //we need into map annotation because we are using map and the exact key here for multibinding
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
