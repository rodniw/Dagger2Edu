package dev.rodni.ru.bitsandpieces.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dev.rodni.ru.bitsandpieces.SessionManager;
import dev.rodni.ru.bitsandpieces.models.User;
import dev.rodni.ru.bitsandpieces.network.auth.AuthApi;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";

    private final AuthApi api;
    private SessionManager sessionManager;

    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi api, SessionManager sessionManager) {
        this.api = api;
        this.sessionManager = sessionManager;
        Log.i("TAG", "working");
    }

    public void authenticateWithId(int id) {
        Log.i(TAG, "Logging");
        sessionManager.authenticateWithId(queryUserId(id));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId) {
        return LiveDataReactiveStreams.fromPublisher(
                api.getUser(userId)
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
    }

    public LiveData<AuthResource<User>> observeAuthState() {
        return sessionManager.getAuthUser();
    }
}
