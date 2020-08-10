package com.example.testmvp.data.db.entity;

import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.room.*;
import com.bumptech.glide.Glide;
import com.example.testmvp.utils.InputsValidation;

@Entity(tableName = "contact", indices = {@Index(value = "conID", unique = true)})
public class Contact {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "conID")
    private Long conID;

    @ColumnInfo(name = "conName")
    private String conName;

    @ColumnInfo(name = "mail")
    private String mail;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "code")
    private String code;

    @Ignore
    private static boolean isValid;

    public Contact() {

        this.conName = "";
        this.mail = "";
        this.date = "";
        this.phone = "";
        this.code = "";
    }

    @Ignore
    public Contact(String name, String mail, String date, String phone, String code) {

        this.conName = name;
        this.mail = mail;
        this.date = date;
        this.phone = phone;
        this.code = code;
    }

    @NonNull
    public Long getConID() {
        return conID;
    }

    public String getConName() {
        return conName;
    }

    public String getMail() {
        return mail;
    }

    public String getDate() {
        return date;
    }

    public String getPhone() {
        return phone;
    }

    public String getCode() {
        return code;
    }

    public void setConID(@NonNull Long conID) {
        this.conID = conID;
    }

    public void setConName(String name) {
        this.conName = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Ignore
    public void setValid(boolean validation){

        isValid = validation;
    }

    @Ignore
    public boolean getValid(){

        return isValid;
    }


    public void setConName(CharSequence name) {
        conName = name.toString();
        charSequenceMutableLiveData.postValue(name);
    }

    public void setMail(CharSequence _mail) {
        mail = _mail.toString();
        charSequenceMutableLiveData.postValue(_mail);
    }

    public void setDate(CharSequence _date) {
        date = _date.toString();
        charSequenceMutableLiveData.postValue(_date);
    }

    public void setPhone(CharSequence _phone) {
        phone = _phone.toString();
        charSequenceMutableLiveData.postValue(_phone);
    }

    public void setCode(CharSequence _code) {
        code = _code.toString();
        charSequenceMutableLiveData.postValue(_code);
    }

    @Ignore
    private static MutableLiveData<CharSequence> charSequenceMutableLiveData = new MutableLiveData<>();

    @Ignore
    @BindingAdapter("app:glide")
    public static void glide(ImageView img,String url){

        Glide.with(img).load(url).into(img);
    }

    @Ignore
    @BindingAdapter("app:checkInputs")
    public static void checkInputs(final EditText textView, final int a) {

        charSequenceMutableLiveData.observeForever(new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {

                isValid = InputsValidation.validate(textView.getText().toString(),a);

                if(isValid)  {

                    textView.setError(null);
                    ;
                }else{
                    textView.setError("wrong");
                }

            }
        });
    }
}
