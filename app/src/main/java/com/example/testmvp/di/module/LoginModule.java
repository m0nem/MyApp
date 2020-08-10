package com.example.testmvp.di.module;

import com.example.testmvp.data.db.dao.UserDao;
import com.example.testmvp.data.db.database.AppDb;
import com.example.testmvp.ui.login.ILogin;
import com.example.testmvp.ui.login.LoginPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    ILogin.IView _view;
    AppDb _userDao;

    public LoginModule(ILogin.IView _view, AppDb appDb) {
        this._view = _view;
        this._userDao = appDb;
    }

    @Provides
    public ILogin.IView get_view() {
        return _view;
    }

    @Provides
    public AppDb get_userDao() {
        return _userDao;
    }

    @Provides
    LoginPresenter providePresenter(){
        return new LoginPresenter(_view , _userDao);
    }


}
