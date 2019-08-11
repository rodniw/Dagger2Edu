package dev.rodni.ru.bitsandpieces.di.auth;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dev.rodni.ru.bitsandpieces.models.User;
import dev.rodni.ru.bitsandpieces.network.auth.AuthApi;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @AuthScope
    @Provides
    @Named("auth_user")
    static User someUser() {
        return new User();
    }

    @AuthScope
    @Provides
    static AuthApi provideApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }
}
