package dev.rodni.ru.bitsandpieces.di.auth;

import dagger.Module;
import dagger.Provides;
import dev.rodni.ru.bitsandpieces.network.auth.AuthApi;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @Provides
    static AuthApi provideApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }
}
