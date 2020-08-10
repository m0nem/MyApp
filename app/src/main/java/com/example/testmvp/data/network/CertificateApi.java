package com.example.testmvp.data.network;

import com.example.testmvp.data.network.model.ApiResultViewModel;
import com.example.testmvp.data.network.model.CertificateData;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CertificateApi {
    public static final String BASED_URL = "http://webapi.ksc.ir/ApiPrivateportal/api/EmploymentCertificate/";
    @Headers({"content-type: application/json; charset=utf-8"})
    @POST("CertificatePreview")
    Single<ApiResultViewModel> CertificatePreview(@Query("token") String token,
                                                  @Body CertificateData certificateData);
}
