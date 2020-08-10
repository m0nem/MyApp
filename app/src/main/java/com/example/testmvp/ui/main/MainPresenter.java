package com.example.testmvp.ui.main;

import android.app.Activity;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.example.testmvp.data.db.database.AppDb;
import com.example.testmvp.data.db.datasource.NewsBoundaryCallBack;
import com.example.testmvp.data.db.datasource.NewsDataSource;
import com.example.testmvp.data.db.entity.News;
import com.example.testmvp.utils.pojo.NetworkState;

public class MainPresenter {

    private AppDb appDb;


    //paging array for reading .
    private LiveData<PagedList<News>> pagedListLiveData;

    NewsDataSource.Factory longNewsFactory;

    //data sourse : type of paging  .  khorak page  up//
    private ItemKeyedDataSource.Factory<Integer, News> dataSourceFactory;

    private PagedList.Config pgConfig = new PagedList.Config.Builder()
            .setInitialLoadSizeHint(2)
            .setPrefetchDistance(10)
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build();
    private MutableLiveData<NetworkState> netStat = new MutableLiveData<>();

    public MainPresenter(AppDb appDb) {
        this.appDb = appDb;
    }


    LiveData<PagedList<News>> getLiveDataList(Context context, Integer start) {
        dataSourceFactory = AppDb.getDatabase(context).newsDao().getList();
        pagedListLiveData = new LivePagedListBuilder(dataSourceFactory, pgConfig)
                .setInitialLoadKey(start)
                .setBoundaryCallback(new NewsBoundaryCallBack(appDb.newsDao(),netStat)) // db & api
                .build();

        return pagedListLiveData;
    }


    LiveData<PagedList<News>> getCustomList() {
        longNewsFactory = new NewsDataSource.Factory(appDb.newsDao(), netStat);
        pagedListLiveData = new LivePagedListBuilder(longNewsFactory, pgConfig)
                .setInitialLoadKey(90l)
                .setBoundaryCallback(new NewsBoundaryCallBack(appDb.newsDao(),netStat))
                .build();

        return pagedListLiveData;

    }


    public void listenForNetState(LifecycleOwner lifecycleOwner){
        netStat.observe(lifecycleOwner, new Observer<NetworkState>() {
            @Override
            public void onChanged(NetworkState networkState) {
                //view.showstate(networkState)
            }
        });
    }
    //use in boundary .or other..... .
    MutableLiveData<NetworkState> getState() {
        return netStat;
    }

    void Refresh() {
        pagedListLiveData.getValue().getDataSource().invalidate();
    }


}
