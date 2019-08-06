package dev.rodni.ru.bitsandpieces.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import dev.rodni.ru.bitsandpieces.R;
import dev.rodni.ru.bitsandpieces.viewmodels.ViewModelProviderFactory;

public class AuthActivity extends DaggerAppCompatActivity {

    private AuthViewModel viewModel;

    @Inject Drawable logo;
    @Inject RequestManager requestManager;
    @Inject ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();
    }

    private void setLogo() {
        requestManager
                .load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }
}
