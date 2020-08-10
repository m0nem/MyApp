package com.example.testmvp.data.db.datasource;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;
import com.example.testmvp.data.db.dao.NewsDao;
import com.example.testmvp.data.db.entity.News;
import com.example.testmvp.utils.DataGenerator;
import com.example.testmvp.utils.pojo.NetworkState;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class NewsBoundaryCallBack extends PagedList.BoundaryCallback<News> {
    String TAG = this.getClass().getName();

    private NewsDao newsDao;
    private MutableLiveData<NetworkState> networkStateMutableLiveData;

    public NewsBoundaryCallBack(NewsDao newsDao, MutableLiveData<NetworkState> networkStateMutableLiveData) {
        super();
        this.newsDao = newsDao;
        this.networkStateMutableLiveData = networkStateMutableLiveData;
    }

    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        Log.e(TAG, "onZeroItemsLoaded");
        networkStateMutableLiveData.postValue(NetworkState.LOADING);
        DataGenerator
                .apiGetNews(1, 100, false, true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        networkStateMutableLiveData.postValue(NetworkState.NETWORK_AVAILABLE);

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<News> news) {
                        networkStateMutableLiveData.postValue(NetworkState.COMPLETED);

                        newsDao
                                .InsertAll(news)
                                .subscribeOn(Schedulers.newThread())
                                .subscribe();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        networkStateMutableLiveData.postValue(NetworkState.FAILED);

                    }
                });
    }

    @Override
    public void onItemAtFrontLoaded(@NonNull News itemAtFront) {
        super.onItemAtFrontLoaded(itemAtFront);
        Log.e(TAG, "onItemAtFrontLoaded");
        networkStateMutableLiveData.postValue(NetworkState.LOADING);


        DataGenerator
                .apiGetNews(itemAtFront.getNewsID(), 100, false, false)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<News> news) {

                        networkStateMutableLiveData.postValue(NetworkState.COMPLETED);

                        newsDao
                                .InsertAll(news)
                                .subscribeOn(Schedulers.newThread())
                                .subscribe();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });


    }

    @Override
    public void onItemAtEndLoaded(@NonNull News itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        Log.e(TAG, "onItemAtEndLoaded");

        networkStateMutableLiveData.postValue(NetworkState.LOADING);


        DataGenerator
                .apiGetNews(itemAtEnd.getNewsID(), 100, true, false)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<News> news) {

                        networkStateMutableLiveData.postValue(NetworkState.NETWORK_AVAILABLE);

                        newsDao
                                .InsertAll(news)
                                .subscribeOn(Schedulers.newThread())
                                .subscribe();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });

    }
}
