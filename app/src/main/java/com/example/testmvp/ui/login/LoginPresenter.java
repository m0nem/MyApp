package com.example.testmvp.ui.login;

import android.util.Log;
import com.example.testmvp.data.db.dao.UserDao;
import com.example.testmvp.data.db.database.AppDb;
import com.example.testmvp.data.db.entity.News;
import com.example.testmvp.data.db.entity.User;
import com.example.testmvp.data.network.CertificateApi;
import com.example.testmvp.data.network.NewsApi;
import com.example.testmvp.data.network.model.ApiFormData;
import com.example.testmvp.data.network.model.ApiResult;
import com.example.testmvp.data.network.model.ApiResultViewModel;
import com.example.testmvp.data.network.model.CertificateData;
import com.example.testmvp.utils.DataGenerator;
import com.example.testmvp.utils.RetrofitFactory;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class LoginPresenter implements ILogin.IPresenter {

    private ILogin.IView view;
    private UserDao userDao;
    private AppDb appDb;

    public LoginPresenter(ILogin.IView _view, final AppDb appDb) {
        this.appDb = appDb;
        userDao = appDb.userDao();
        view = _view;
    }


    private void InsertUser(User user) {


        userDao.Insert(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Long>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Long> longs) {

                        view.showMassageLogin("ثبت شد");
                        view.apiResult(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        view.showMassageLogin("مشکل در است . ");
                    }
                });


    }

    @Override
    public void login(final com.example.testmvp.data.network.model.User user) {

        if (!user.isValid()) {
            view.showMassageLogin("اطلاعات نا معتبر");
            return;
        }
        CertificateApi api = RetrofitFactory.createRequest(CertificateApi.class, CertificateApi.BASED_URL);
        view.showloadingLogin();

        /*{
            "CertificateTypeId":1,
                "OrganizationDescription":"ttt",
                "OrganizationId":1,
                "RequestReason":"gffg",
                "RequestUser":"عليرضا شريفي"
        }*/

        CertificateData certificateData =
                new CertificateData(1,
                        "asasas",
                        1,
                        "asdfasdf",
                        "test");

        api.CertificatePreview("3bd3ff1d-2d45-4068-b308-a14bc43c99cf", certificateData)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ApiResultViewModel>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ApiResultViewModel apiResultViewModel) {

                        view.hideLoadingLogin();

//                        User _user = (User) apiResultViewModel.getConvertedData(User.class);

                        if (apiResultViewModel.getSuccess() &&
                                apiResultViewModel.getErrorCode() == 200) {

                            ApiResult apiResult = (ApiResult) apiResultViewModel.getConvertedData(ApiResult.class);
                            view.apiResultCertificate(apiResult.getResult());

//                            view.apiResult(true);


                        } else {

                            view.apiResult(false);
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
//                        InsertUser(new User("asas", "sdsd"));
                        view.hideLoadingLogin();
                        view.showMassageLogin(e.getMessage());
                    }
                });

    }

    @Override
    public void fillDb() {

        DataGenerator.apiGetNews(20, 200, true, true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<News> news) {
                        insertNews(news);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    private void insertNews(List<News> news) {

        appDb.newsDao().InsertAll(news)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Long>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Long> longs) {
                        Log.e("insertNews", longs.size() + "");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("insertNews", e.getMessage());
                    }
                });
    }

}
