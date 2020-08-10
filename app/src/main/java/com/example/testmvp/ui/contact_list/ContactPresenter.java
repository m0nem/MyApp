package com.example.testmvp.ui.contact_list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.example.testmvp.data.db.database.AppDb;
import com.example.testmvp.data.db.datasource.ContactDataSource;
import com.example.testmvp.data.db.entity.Contact;
import com.example.testmvp.ui.contact_add.AddContactActivity;
import com.example.testmvp.utils.pojo.NetworkState;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ContactPresenter implements IContact.IPresenter{

   private AppDb appDb;
    private IContact.IView view;
    private Activity activity;

    //paging array for reading .
    private LiveData<PagedList<Contact>> pagedListLiveData;
    private ContactDataSource.Factory longContactFactory;

    private ItemKeyedDataSource.Factory<Integer, Contact> dataSourceFactory;

    private PagedList.Config pgConfig = new PagedList.Config.Builder()
//            .setInitialLoadSizeHint(10)
//            .setPrefetchDistance(10)
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build();

    private MutableLiveData<NetworkState> netStat = new MutableLiveData<>();

    public ContactPresenter(AppDb appDb ,  IContact.IView _view, Activity activity){

        this.appDb = appDb;
        this.view = _view;
        this.activity = activity;
    }

    LiveData<PagedList<Contact>> getLiveDataList(Context context, Integer start) {
        dataSourceFactory = AppDb.getDatabase(context).contactDao().getList();
        pagedListLiveData = new LivePagedListBuilder(dataSourceFactory, pgConfig)
                .setInitialLoadKey(start)
               // .setBoundaryCallback(new NewsBoundaryCallBack(appDb.newsDao(),netStat)) // db & api
                .build();

        return pagedListLiveData;
    }


    @Override
    public LiveData<PagedList<Contact>> getCustomList() {
        longContactFactory = new ContactDataSource.Factory(appDb.contactDao(), netStat);
        pagedListLiveData = new LivePagedListBuilder(longContactFactory, pgConfig)
             //   .setInitialLoadKey(1l)
                .build();
        return pagedListLiveData;
    }

    public void deleteItem(Contact contact){

        appDb.contactDao().DeleteFromContact(contact)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {


                        view.afterDelete();
                        pagedListLiveData.getValue().getDataSource().invalidate();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Log.i("Error ; " , e.getMessage());
                    }
                });

    }

    //use in boundary .or other..... .
    @Override
    public void listenForNetState(LifecycleOwner lifecycleOwner) {

        netStat.observe(lifecycleOwner, new Observer<NetworkState>() {
            @Override
            public void onChanged(NetworkState networkState) {
                view.showState(networkState);
            }
        });
    }

    @Override
    public void goToAdd() {

        Intent intent = new Intent(activity, AddContactActivity.class);
        activity.startActivity(intent);
    }



}
