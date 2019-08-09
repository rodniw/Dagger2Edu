package dev.rodni.ru.bitsandpieces.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import dev.rodni.ru.bitsandpieces.R;
import dev.rodni.ru.bitsandpieces.viewmodels.ViewModelProviderFactory;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private EditText userInput;
    private ProgressBar progressBar;

    private AuthViewModel viewModel;

    @Inject Drawable logo;
    @Inject RequestManager requestManager;
    @Inject ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        userInput = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.login_button).setOnClickListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();

        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.observeAuthState().observe(this, userAuthResource -> {
            if (userAuthResource != null) {
                switch (userAuthResource.status) {
                    case ERROR:
                        showProgressBar(false);
                        Toast.makeText(
                                AuthActivity.this,
                                userAuthResource.message + getString(R.string.error_login_question),
                                Toast.LENGTH_SHORT).show();
                        break;
                    case AUTHENTICATED:
                        showProgressBar(false);
                        Log.i("TAG", userAuthResource.data.getEmail());
                        break;
                    case NOT_AUTHENTICATED:
                        showProgressBar(false);
                        break;
                    case LOADING:
                        showProgressBar(true);
                        break;
                }
            }
        });
    }

    private void showProgressBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
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
