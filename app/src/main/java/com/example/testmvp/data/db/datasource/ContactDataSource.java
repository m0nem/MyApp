package com.example.testmvp.data.db.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import com.example.testmvp.TestMvp;
import com.example.testmvp.data.db.dao.ContactDao;
import com.example.testmvp.data.db.entity.Contact;
import com.example.testmvp.utils.ApiContactHelper;
import com.example.testmvp.utils.pojo.NetworkState;
import com.example.testmvp.utils.pojo.Result;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class ContactDataSource extends ItemKeyedDataSource<Long , Contact> {

    public static class Factory extends ItemKeyedDataSource.Factory<Long, Contact> {

        public Factory(ContactDao contactDao, MutableLiveData<NetworkState> networkStateMutableLiveData) {
            this.contactDao = contactDao;
            this.networkStateMutableLiveData = networkStateMutableLiveData;

        }

        @Override
        public DataSource<Long, Contact> create() {
            ContactDataSource dataSource = new ContactDataSource(contactDao, networkStateMutableLiveData);
            // Keep reference to the data source with a MutableLiveData reference
            return dataSource;
        }

        private ContactDao contactDao;
        private MutableLiveData<NetworkState> networkStateMutableLiveData;


    }

    private ContactDao contactDao;
    private MutableLiveData<NetworkState> netState;
    private ApiContactHelper apiContactHelper;

    public ContactDataSource(ContactDao contactDao, MutableLiveData<NetworkState> netState) {
        this.contactDao = contactDao;
        this.netState = netState;
        this.apiContactHelper = new ApiContactHelper(contactDao, netState);

    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Contact> callback) {

        netState.postValue(NetworkState.LOADING);

        contactDao.getInitialLoad(params.requestedLoadSize, params.requestedInitialKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<List<Contact>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<Contact> contacts) {

                        if (contacts.size() == 0) {
                            if(TestMvp.UsingApi) {

                                apiContactHelper.onZeroItemsLoaded()
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
                            }

                        } else {
                            callback.onResult(contacts);
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
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Contact> callback) {

        netState.postValue(NetworkState.LOADING);
        contactDao.getLoadAfter(params.requestedLoadSize, params.key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<List<Contact>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<Contact> contacts) {

                        if (contacts.size() == 0) {
                            if(TestMvp.UsingApi) {

                                apiContactHelper.onItemAtEndLoaded(params.key)
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
                            }

                        } else {
                            callback.onResult(contacts);
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
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Contact> callback) {


        netState.postValue(NetworkState.LOADING);
        contactDao.getLoadBefore(params.requestedLoadSize, params.key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<List<Contact>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<Contact> contacts) {

                        if (contacts.size() == 0) {
                            if(TestMvp.UsingApi) {
                                apiContactHelper.onItemAtFrontLoaded(params.key)
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
                            }
                        } else {
                            callback.onResult(contacts);
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
    public Long getKey(@NonNull Contact item) {
        return item.getConID();
    }

}
