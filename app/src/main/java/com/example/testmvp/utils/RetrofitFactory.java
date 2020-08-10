package com.example.testmvp.utils;


import com.example.testmvp.di.component.ApplicationComponent;
import com.example.testmvp.di.component.DaggerApplicationComponent;
import com.example.testmvp.di.module.ApplicationModule;

public class RetrofitFactory<T> {
  //private final static String mainUrl = "";
  public static <T> T createRequest(final Class<T> tClass,String baseUrl) {
        ApplicationComponent applicationComponent=
              DaggerApplicationComponent.builder()
                      .applicationModule(new ApplicationModule(baseUrl)).build();
      return applicationComponent.providedRetrofit().create(tClass);

  }
}

