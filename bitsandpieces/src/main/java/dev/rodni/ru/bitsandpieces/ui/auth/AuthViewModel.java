package dev.rodni.ru.bitsandpieces.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dev.rodni.ru.bitsandpieces.models.User;
import dev.rodni.ru.bitsandpieces.network.auth.AuthApi;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private final AuthApi api;

    private MediatorLiveData<User> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi api) {
        this.api = api;
        Log.i("TAG", "working");
    }

    public void authenticateWithId(int id) {
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
                api.getUser(id)
                .subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, user -> {
            authUser.setValue(user);
            authUser.removeSource(source);
        });
    }

    public LiveData<User> observeUser() {
        return authUser;
    }
}
