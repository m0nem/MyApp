package com.example.testmvp.utils;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.example.testmvp.data.db.dao.ContactDao;
import com.example.testmvp.data.db.entity.Contact;
import com.example.testmvp.utils.DataGenerator;
import com.example.testmvp.utils.pojo.NetworkState;
import com.example.testmvp.utils.pojo.Result;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

import java.util.List;

public class ApiContactHelper {
    String TAG = this.getClass().getName();

    private ContactDao contactDao;
    private MutableLiveData<NetworkState> networkStateMutableLiveData;


    public ApiContactHelper(ContactDao contactDao, MutableLiveData<NetworkState> networkStateMutableLiveData) {
        super();
        this.contactDao = contactDao;
        this.networkStateMutableLiveData = networkStateMutableLiveData;
    }

    public Single<Result> onZeroItemsLoaded() {
        return Single.create(new SingleOnSubscribe<Result>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull SingleEmitter<Result> emitter) throws Exception {
                Result result = new Result();
                try {

                    Log.e(TAG, "onZeroItemsLoaded");
                    networkStateMutableLiveData.postValue(NetworkState.LOADING);
                    List<Contact> list = DataGenerator
                            .apiGetContactHelper(1, 100, false, true);
                    if (list.size() == 0) {
                        result.setEndOfData(true);
                    } else {
                        contactDao.InsertAllHelper(list);
                    }
                    networkStateMutableLiveData.postValue(NetworkState.NETWORK_AVAILABLE);

                } catch (Exception e) {
                    emitter.onError(e);
                }
                emitter.onSuccess(result);
            }
        });
    }


    public Single<Result> onItemAtFrontLoaded(@NonNull final Long contactId) {
        return Single.create(new SingleOnSubscribe<Result>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull SingleEmitter<Result> emitter) throws Exception {
                Result result = new Result();
                try {

                    Log.e(TAG, "onItemAtFrontLoaded");
                    networkStateMutableLiveData.postValue(NetworkState.LOADING);
                    List<Contact> list = DataGenerator
                            .apiGetContactHelper(contactId, 100, false, false);
                    if (list.size() == 0) {
                        result.setEndOfData(true);
                    } else {
                        contactDao.InsertAllHelper(list);
                    }
                    networkStateMutableLiveData.postValue(NetworkState.NETWORK_AVAILABLE);

                } catch (Exception e) {
                    emitter.onError(e);
                }
                emitter.onSuccess(result);
            }
        });
    }


    public Single<Result> onItemAtEndLoaded(@NonNull final Long contactId) {
        return Single.create(new SingleOnSubscribe<Result>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull SingleEmitter<Result> emitter) throws Exception {
                Result result = new Result();
                try {

                    Log.e(TAG, "onItemAtEndLoaded");
                    networkStateMutableLiveData.postValue(NetworkState.LOADING);
                    List<Contact> list = DataGenerator
                            .apiGetContactHelper(contactId, 100, true, false);
                    if (list.size() == 0) {
                        result.setEndOfData(true);
                    } else {
                        contactDao.InsertAllHelper(list);
                    }
                    networkStateMutableLiveData.postValue(NetworkState.NETWORK_AVAILABLE);

                } catch (Exception e) {
                    emitter.onError(e);
                }
                emitter.onSuccess(result);
            }
        });
    }


}
