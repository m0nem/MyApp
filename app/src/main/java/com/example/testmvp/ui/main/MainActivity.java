package com.example.testmvp.ui.main;

import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.testmvp.R;
import com.example.testmvp.data.db.database.AppDb;
import com.example.testmvp.data.db.entity.News;
import com.example.testmvp.databinding.ActivityMainBinding;
import com.example.testmvp.ui.base.BaseActivity;
import com.example.testmvp.utils.pojo.NetworkState;

public class MainActivity extends BaseActivity {


    MainPresenter mainPresenter;
    ActivityMainBinding binding;

    NewsRvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainPresenter = new MainPresenter(AppDb.getDatabase(this));
        mainPresenter.listenForNetState(this);
        binding.rvNews
                .setLayoutManager(new LinearLayoutManager(this,
                        RecyclerView.VERTICAL,
                        false));

        adapter = new NewsRvAdapter(this);

        binding.rvNews.setAdapter(adapter);

        adapter.submitList(null);
        adapter.notifyDataSetChanged();


        mainPresenter
                .getCustomList()
             //   .getLiveDataList(this, 20)
                .observe(this, new Observer<PagedList<News>>() {
                    @Override
                    public void onChanged(PagedList<News> news) {
                        adapter.submitList(news);
                    }
                });

        binding.srlReload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.srlReload.setRefreshing(false);
                mainPresenter.Refresh();
            }
        });

        mainPresenter.getState().observe(this, new Observer<NetworkState>() {
            @Override
            public void onChanged(NetworkState networkState) {
                Toast.makeText(MainActivity.this, networkState.name(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
