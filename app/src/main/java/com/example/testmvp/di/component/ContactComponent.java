package com.example.testmvp.di.component;

import com.example.testmvp.di.module.ContactModule;
import com.example.testmvp.ui.contact_list.ContactPresenter;
import dagger.Component;

@Component(modules = ContactModule.class)
public interface ContactComponent {

    ContactPresenter providePresenter();

}
