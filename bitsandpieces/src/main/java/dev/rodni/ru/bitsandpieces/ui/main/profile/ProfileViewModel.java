package dev.rodni.ru.bitsandpieces.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dev.rodni.ru.bitsandpieces.SessionManager;
import dev.rodni.ru.bitsandpieces.models.User;
import dev.rodni.ru.bitsandpieces.ui.auth.AuthResource;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";
    private final SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        Log.i(TAG, "Profile view model working...");
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser() {
        return sessionManager.getAuthUser();
    }
}
