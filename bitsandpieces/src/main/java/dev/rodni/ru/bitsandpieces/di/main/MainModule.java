package dev.rodni.ru.bitsandpieces.di.main;

import android.app.Application;
import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import dev.rodni.ru.bitsandpieces.network.main.MainApi;
import dev.rodni.ru.bitsandpieces.ui.main.posts.PostsRecyclerAdapter;
import dev.rodni.ru.bitsandpieces.utils.VerticalSpacingItemDecoration;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static PostsRecyclerAdapter provideAdapter() {
        return new PostsRecyclerAdapter();
    }

    @Provides
    static LinearLayoutManager provideLayoutManager(Application application) {
        return new LinearLayoutManager(application.getApplicationContext());
    }

    @MainScope
    @Provides
    static VerticalSpacingItemDecoration provideDecorator() {
        return new VerticalSpacingItemDecoration(15);
    }

    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }
}
