package dev.rodni.ru.bitsandpieces.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import dev.rodni.ru.bitsandpieces.R;
import dev.rodni.ru.bitsandpieces.models.Post;
import dev.rodni.ru.bitsandpieces.ui.main.Resource;
import dev.rodni.ru.bitsandpieces.viewmodels.ViewModelProviderFactory;

public class PostsFragment extends DaggerFragment {
    private static final String TAG = "PostsFragment";

    private PostsViewModel viewModel;
    private RecyclerView recycler;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recycler = view.findViewById(R.id.recycler_view);

        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel.class);

        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), listResource -> {
            if (listResource != null) {
                Log.i(TAG, "onChanged: " + listResource.data);
            }
        });
    }
}
