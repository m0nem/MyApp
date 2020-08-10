package com.example.testmvp.data.network;

import com.example.testmvp.data.network.model.ApiResultViewModel;
import com.example.testmvp.data.network.model.NewsViewModel;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface NewsApi {

    public static final String BASED_URL="https://healthiar.com";

    @GET("PackageInfo/GetPackageInfo")
    Single<List<NewsViewModel>> GetAllNews();



    @Headers({
            "content-type: application/x-www-form-urlencoded"
    })

    @GET("PackageInfo/GetPackageInfo")
    Single<ApiResultViewModel> Login(@Query("username") String username , @Query("pass") String pass );
    //@Body("user") User user : if using post and send model or files



}
