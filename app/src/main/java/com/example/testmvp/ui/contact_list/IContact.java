package com.example.testmvp.ui.contact_list;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import com.example.testmvp.data.db.entity.Contact;
import com.example.testmvp.utils.pojo.NetworkState;

public interface IContact {

    interface IPresenter{
        LiveData<PagedList<Contact>> getCustomList();
        void listenForNetState(LifecycleOwner lifecycleOwner);
        void goToAdd();
        void deleteItem(Contact contact);


    }

    interface IView{
       void showState(NetworkState state);
       void afterDelete();

    }
}
