package com.example.testmvp.data.network.model;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class User {
    String User;
    String Password;

    boolean valid = false;


    public User() {
    }

    public User(String user, String password) {
        User = user;
        Password = password;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }


    @BindingAdapter("app:validation")
    public static void validation(final EditText editText, CharSequence charSequence) {


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {


            }

            @Override
            public void afterTextChanged(Editable s) {
                // editText.setText(s.toString().replace(" ", ""));
                if (s.toString().contains(" ")) {
                    editText.setText(s.toString().trim().replace(" ", ""));
                    editText.setSelection(editText.getText().toString().length());
                }
            }
        });
    }


}
