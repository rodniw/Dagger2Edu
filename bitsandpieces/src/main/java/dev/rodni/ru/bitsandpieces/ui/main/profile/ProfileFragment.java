package dev.rodni.ru.bitsandpieces.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import dagger.android.support.DaggerFragment;
import dev.rodni.ru.bitsandpieces.R;

public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Profile fragment", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
