package com.example.testmvp.data.db.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import com.example.testmvp.data.db.dao.NewsDao;
import com.example.testmvp.data.db.entity.News;
import com.example.testmvp.utils.ApiNewsHelper;
import com.example.testmvp.utils.pojo.NetworkState;
import com.example.testmvp.utils.pojo.Result;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;


public class NewsDataSource extends ItemKeyedDataSource<Long, News> {

    public static class Factory extends ItemKeyedDataSource.Factory<Long, News> {

        public Factory(NewsDao newsDao, MutableLiveData<NetworkState> networkStateMutableLiveData) {
            this.newsDao = newsDao;
            this.networkStateMutableLiveData = networkStateMutableLiveData;
        }

        @Override
        public DataSource<Long, News> create() {
            NewsDataSource dataSource = new NewsDataSource(newsDao, networkStateMutableLiveData);
            // Keep reference to the data source with a MutableLiveData reference
            return dataSource;
        }

        private NewsDao newsDao;
        private MutableLiveData<NetworkState> networkStateMutableLiveData;

    }


    private NewsDao newsDao;
    private MutableLiveData<NetworkState> netState;
    private ApiNewsHelper apiNewsHelper;

    public NewsDataSource(NewsDao newsDao, MutableLiveData<NetworkState> networkStateMutableLiveData) {
        this.newsDao = newsDao;
        this.netState = networkStateMutableLiveData;
        apiNewsHelper = new ApiNewsHelper(newsDao, networkStateMutableLiveData);
    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<News> callback) {

        netState.postValue(NetworkState.LOADING);
        newsDao.getInitialLoad(params.requestedLoadSize, params.requestedInitialKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<News> news) {

                        if (news.size() == 0) {
                            apiNewsHelper.onZeroItemsLoaded()
                                    .subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new SingleObserver<Result>() {
                                        @Override
                                        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                                        }

                                        @Override
                                        public void onSuccess(@io.reactivex.annotations.NonNull Result result) {
                                            if (!result.isEndOfData())
                                                loadInitial(params, callback);
                                        }

                                        @Override
                                        public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                                        }
                                    });
                        } else {
                            callback.onResult(news);
                            netState.postValue(NetworkState.COMPLETED);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        netState.postValue(NetworkState.ERROR);
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<News> callback) {
        netState.postValue(NetworkState.LOADING);
        newsDao.getLoadAfter(params.requestedLoadSize, params.key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<News> news) {

                        if (news.size() == 0) {
                            apiNewsHelper.onItemAtEndLoaded(params.key)
                                    .subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new SingleObserver<Result>() {
                                        @Override
                                        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                                        }

                                        @Override
                                        public void onSuccess(@io.reactivex.annotations.NonNull Result result) {
                                            if (!result.isEndOfData())
                                                loadAfter(params, callback);
                                        }

                                        @Override
                                        public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                                        }
                                    });
                        } else {
                            callback.onResult(news);
                            netState.postValue(NetworkState.COMPLETED);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        netState.postValue(NetworkState.ERROR);
                    }
                });


    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<News> callback) {

        netState.postValue(NetworkState.LOADING);
        newsDao.getLoadBefore(params.requestedLoadSize, params.key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<News> news) {

                        if (news.size() == 0) {
                            apiNewsHelper.onItemAtFrontLoaded(params.key)
                                    .subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new SingleObserver<Result>() {
                                        @Override
                                        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                                        }

                                        @Override
                                        public void onSuccess(@io.reactivex.annotations.NonNull Result result) {
                                            if (!result.isEndOfData())
                                                loadBefore(params, callback);
                                        }

                                        @Override
                                        public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                                        }
                                    });
                        } else {
                            callback.onResult(news);
                            netState.postValue(NetworkState.COMPLETED);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        netState.postValue(NetworkState.ERROR);
                    }
                });
    }

    @NonNull
    @Override
    public Long getKey(@NonNull News item) {
        return item.getNewsID();
    }


}
