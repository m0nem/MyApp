package com.example.testmvp.di.component;


import javax.inject.Singleton;

import com.example.testmvp.di.module.ApplicationModule;
import dagger.Component;

import retrofit2.Retrofit;
@Component(modules = ApplicationModule.class)
@Singleton
public interface ApplicationComponent {
  Retrofit providedRetrofit();
}


