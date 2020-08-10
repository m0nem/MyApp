package com.example.testmvp.di.module;

import android.app.Activity;
import android.content.Context;
import com.example.testmvp.data.db.database.AppDb;
import com.example.testmvp.ui.contact_list.ContactPresenter;
import com.example.testmvp.ui.contact_list.IContact;
import dagger.Module;
import dagger.Provides;

@Module
public class ContactModule {

    IContact.IView _view;
    AppDb _conDao;
    Activity activity;

    public ContactModule(IContact.IView _view, AppDb appDb , Activity activity) {
        this._view = _view;
        this._conDao = appDb;
        this.activity = activity;
    }

    @Provides
    public IContact.IView get_view() {
        return _view;
    }

    @Provides
    public Activity get_activity() {
        return activity;
    }


    @Provides
    public AppDb get_Dao() {
        return _conDao;
    }

    @Provides
    ContactPresenter providePresenter(){
        return new ContactPresenter(_conDao , _view,activity);
    }


}
