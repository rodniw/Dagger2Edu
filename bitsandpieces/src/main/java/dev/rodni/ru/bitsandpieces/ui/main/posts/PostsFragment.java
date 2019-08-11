package dev.rodni.ru.bitsandpieces.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import dev.rodni.ru.bitsandpieces.R;
import dev.rodni.ru.bitsandpieces.utils.VerticalSpacingItemDecoration;
import dev.rodni.ru.bitsandpieces.viewmodels.ViewModelProviderFactory;

public class PostsFragment extends DaggerFragment {
    private static final String TAG = "PostsFragment";

    private PostsViewModel viewModel;
    private RecyclerView recycler;

    @Inject
    PostsRecyclerAdapter adapter;
    @Inject
    LinearLayoutManager layoutManager;
    @Inject
    VerticalSpacingItemDecoration decorator;
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

        initRecyclerView();
        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), listResource -> {
            if (listResource != null) {
                Log.i(TAG, "onChanged: " + listResource.data);
                switch (listResource.status) {
                    case LOADING:
                        Log.d(TAG, "subscribeObservers: loading");
                        break;
                    case SUCCESS:
                        Log.d(TAG, "subscribeObservers: success");
                        adapter.setPosts(listResource.data);
                        break;
                    case ERROR:
                        Log.d(TAG, "subscribeObservers: ERROR " + listResource.message);
                        break;
                }
            }
        });
    }

    private void initRecyclerView() {
        recycler.setLayoutManager(layoutManager);
        recycler.addItemDecoration(decorator);
        recycler.setAdapter(adapter);
    }
}
