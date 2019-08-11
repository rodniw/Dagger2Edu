package dev.rodni.ru.bitsandpieces.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dev.rodni.ru.bitsandpieces.BaseApplication;
import dev.rodni.ru.bitsandpieces.SessionManager;

//we extend android injector not to write inject method here e t c
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                AppModule.class,
                ViewModelFactoryModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    //by this we provide the session manager through all the application
    //and its gonna be accessible by any class that will inject it inside the class
    SessionManager sessionManager();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
