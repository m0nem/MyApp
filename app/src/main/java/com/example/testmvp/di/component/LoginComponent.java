package com.example.testmvp.di.component;

import com.example.testmvp.di.module.LoginModule;
import com.example.testmvp.ui.login.LoginPresenter;
import dagger.Component;

@Component(modules = LoginModule.class)
public interface LoginComponent {

    LoginPresenter providePresenter();



}
