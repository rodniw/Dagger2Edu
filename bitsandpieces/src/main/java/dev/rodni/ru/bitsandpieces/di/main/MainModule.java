package dev.rodni.ru.bitsandpieces.di.main;

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

    @Provides
    static PostsRecyclerAdapter provideAdapter() {
        return new PostsRecyclerAdapter();
    }

    /*@Provides
    static LinearLayoutManager provideLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }*/

    @Provides
    static VerticalSpacingItemDecoration provideDecorator() {
        return new VerticalSpacingItemDecoration(15);
    }

    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }
}
