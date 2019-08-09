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
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private final AuthApi api;

    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi api) {
        this.api = api;
        Log.i("TAG", "working");
    }

    public void authenticateWithId(int id) {
        //for ui progress bar
        authUser.setValue(AuthResource.loading(null));

        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
                api.getUser(id)
                        //in case of some error return from the api
                        .onErrorReturn(throwable -> {
                            User user = new User();
                            user.setId(-1);
                            return user;
                        })
                        //this will work with responses user, in case of some error too
                        .map((Function<User, AuthResource<User>>) user -> {
                            if (user.getId() == -1) {
                                return AuthResource.error("Could not auth", null);
                            }
                            return AuthResource.authenticated(user);
                        })
                .subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, (AuthResource<User> user) -> {
            authUser.setValue(user);
            authUser.removeSource(source);
        });
    }

    public LiveData<AuthResource<User>> observeUser() {
        return authUser;
    }
}
