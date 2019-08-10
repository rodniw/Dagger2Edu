package dev.rodni.ru.bitsandpieces.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dev.rodni.ru.bitsandpieces.SessionManager;
import dev.rodni.ru.bitsandpieces.network.main.MainApi;

public class PostsViewModel extends ViewModel {
    private static final String TAG = "PostsViewModel";
    private final SessionManager sessionManager;
    private final MainApi api;


    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi api) {
        this.sessionManager = sessionManager;
        this.api = api;
        Log.i(TAG, "PostsViewModel: working");
    }
}
