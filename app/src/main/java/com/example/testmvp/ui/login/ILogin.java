package com.example.testmvp.ui.login;

import com.example.testmvp.data.db.entity.User;
import com.example.testmvp.data.network.model.ApiFormData;

public interface ILogin {
    //shift + f6 : rename easy .
    interface IView {

        void apiResult(boolean isSucess);

        void apiResultCertificate(ApiFormData apiFormData);

        void showloadingLogin();

        void hideLoadingLogin();

        void showMassageLogin(String Massage);

    }

    interface IPresenter {


        void login(com.example.testmvp.data.network.model.User user);

        void fillDb();

    }
}
