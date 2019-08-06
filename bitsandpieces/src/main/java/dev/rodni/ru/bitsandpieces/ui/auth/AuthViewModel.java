package dev.rodni.ru.bitsandpieces.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    @Inject
    public AuthViewModel() {
        Log.i("TAG", "working");
    }
}
