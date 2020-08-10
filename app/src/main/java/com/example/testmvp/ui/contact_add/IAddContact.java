package com.example.testmvp.ui.contact_add;

import com.example.testmvp.data.db.entity.Contact;

public interface IAddContact {

    interface IPresenter{

        void insertContact(Contact contact);

    }

    interface IView{

        void afterInsert(boolean isSuccess);

    }

}
