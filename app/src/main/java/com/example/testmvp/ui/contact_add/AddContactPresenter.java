package com.example.testmvp.ui.contact_add;

import com.example.testmvp.data.db.database.AppDb;
import com.example.testmvp.data.db.entity.Contact;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddContactPresenter implements IAddContact.IPresenter{

    AppDb appdb;
    IAddContact.IView view;

    public AddContactPresenter(AppDb appdb , IAddContact.IView view){

        this.appdb = appdb;
        this.view = view;
    }

    @Override
    public void insertContact(Contact contact) {

        if(!contact.getValid()) {

            view.afterInsert(false);
            return;

        }
                appdb.contactDao()
                        .Insert(contact)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Long l) {

                        view.afterInsert(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        view.afterInsert(false);
                    }
                });

    }
}
