package com.example.testmvp.ui.login;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.testmvp.R;
import com.example.testmvp.data.db.database.AppDb;
import com.example.testmvp.data.network.model.ApiFormData;
import com.example.testmvp.data.network.model.User;
import com.example.testmvp.databinding.ActivityLoginBinding;
import com.example.testmvp.di.component.DaggerLoginComponent;
import com.example.testmvp.di.component.LoginComponent;
import com.example.testmvp.di.module.LoginModule;
import com.example.testmvp.ui.base.BaseActivity;
import com.example.testmvp.ui.main.MainActivity;

public class LoginActivity extends BaseActivity implements ILogin.IView {

    LoginPresenter presenter;

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LoginComponent component = DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this, AppDb.getDatabase(this))).build();
        presenter = component.providePresenter();

        binding.setModel(new User("", ""));
        binding.setPresenter(presenter);

    }

    @Override
    public void apiResult(boolean isSucess) {

        if (isSucess) {

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "نشد", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void apiResultCertificate(ApiFormData apiFormData) {
        Toast.makeText(this, apiFormData.getNameFamily(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showloadingLogin() {

        //  showDialog("");
    }

    @Override
    public void hideLoadingLogin() {

//        hideDialog();
    }

    @Override
    public void showMassageLogin(String Massage) {

        // showDialog(Massage);

    }
}
