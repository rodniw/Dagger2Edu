package dev.rodni.ru.bitsandpieces.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import dev.rodni.ru.bitsandpieces.R;
import dev.rodni.ru.bitsandpieces.models.User;
import dev.rodni.ru.bitsandpieces.viewmodels.ViewModelProviderFactory;

public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";

    private ProfileViewModel viewModel;

    private TextView email, username, website;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated: profile fragment was created");
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);

        viewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class);

        subscribeObservers();
    }

    //first of all cleaning of all the data
    //fragment have some different lifecycle
    //that is why we need to remove prev observers before assigning new ones
    private void subscribeObservers() {
        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), userAuthResource -> {
            if (userAuthResource != null) {
                switch (userAuthResource.status) {
                    case AUTHENTICATED:
                        setUserDetails(userAuthResource.data);
                        break;
                    case ERROR:
                        setErrorDetails(userAuthResource.message);
                        break;
                }
            }
        });
    }

    //simple method that setting users field to the view
    private void setUserDetails(User data) {
        email.setText(data.getEmail());
        username.setText(data.getUsername());
        website.setText(data.getWebsite());
    }

    //simple method that shows an error
    private void setErrorDetails(String message) {
        email.setText(message);
        username.setText("error");
        website.setText("");
    }
}
