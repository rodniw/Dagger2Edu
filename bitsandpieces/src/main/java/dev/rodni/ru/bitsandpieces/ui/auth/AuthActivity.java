package dev.rodni.ru.bitsandpieces.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import dev.rodni.ru.bitsandpieces.R;
import dev.rodni.ru.bitsandpieces.models.User;
import dev.rodni.ru.bitsandpieces.viewmodels.ViewModelProviderFactory;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private EditText userInput;

    private AuthViewModel viewModel;

    @Inject Drawable logo;
    @Inject RequestManager requestManager;
    @Inject ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        userInput = findViewById(R.id.user_id_input);

        findViewById(R.id.login_button).setOnClickListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();

        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.observeUser().observe(this, user -> {
            if (user != null) {
                Log.i("tag", "user not null");
            }
        });
    }

    private void setLogo() {
        requestManager
                .load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                attemptLogin();
                break;
        }
    }

    private void attemptLogin() {
        if (TextUtils.isEmpty(userInput.getText().toString())) {
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(userInput.getText().toString()));
    }
}
