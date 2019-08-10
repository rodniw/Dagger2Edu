package dev.rodni.ru.bitsandpieces.di.main;

import dagger.Module;
import dagger.Provides;
import dev.rodni.ru.bitsandpieces.network.main.MainApi;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }
}
