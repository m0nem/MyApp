package com.example.testmvp.di.module;


import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

  private String mBaseUrl;


  public ApplicationModule(String baseUrl) {

    mBaseUrl = baseUrl;
  }

  @Singleton
  @Provides
  GsonConverterFactory provideGsonConverterFactory() {

    return GsonConverterFactory.create();
  }

 /* @Singleton
  @Provides
  @Named("ok-1")
  OkHttpClient provideOkHttpClient1() {
    return new OkHttpClient.Builder()
      .connectTimeout(1000, TimeUnit.SECONDS)
      .readTimeout(1000, TimeUnit.SECONDS)
      .build();
  }
*/
  @Singleton
  @Provides
  @Named("ok-1")
  OkHttpClient provideOkHttpClient2() {
    return new OkHttpClient.Builder()
      .connectTimeout(10000, TimeUnit.SECONDS)
      .readTimeout(100000, TimeUnit.SECONDS)
      .build();
  }



  @Singleton
  @Provides
  Retrofit provideRetrofit(@Named("ok-1") OkHttpClient client, GsonConverterFactory converterFactory, RxJava2CallAdapterFactory adapterFactory  ) {
    return new Retrofit.Builder()
      .baseUrl(mBaseUrl)
      .addConverterFactory(converterFactory)
      //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addCallAdapterFactory(adapterFactory)
      .client(client)
      .build();
  }
  @Singleton
  @Provides
  RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory(){
    return RxJava2CallAdapterFactory.create();
  }
}
