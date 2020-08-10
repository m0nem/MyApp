package com.example.testmvp.data.db.entity;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.*;
import androidx.room.*;

@Entity(tableName = "news", indices = {@Index(value = "NewsID", unique = true)})
public class News {


    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "NewsID")
    Long NewsID;

    @ColumnInfo(name = "Title")
    String Title;
    @ColumnInfo(name = "Body")
    String Body;
    @ColumnInfo(name = "Date")
    String Date;

    public News() {

        Title = "null";
        Body = "null";
        Date = "null";
    }

    @Ignore
    public News(Long newsID, String title, String body, String date) {
        NewsID = newsID;
        Title = title;
        Body = body;
        Date = date;
    }


    public Long getNewsID() {
        return NewsID;
    }

    public void setNewsID(Long newsID) {
        NewsID = newsID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }






    public void setBody(CharSequence body) {
        Body = body.toString();
        charSequenceMutableLiveData.postValue(body);
    }


    @Ignore
    private static MutableLiveData<CharSequence> charSequenceMutableLiveData = new MutableLiveData<>();

    @BindingAdapter("app:test")
    public static void test(final EditText textView, String a) {
        charSequenceMutableLiveData.observeForever(new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                if (charSequence.toString().contains("#")) {
//                    textView.setText(charSequence.toString().replace("#", ""));
                    textView.setError("dasd");
                } else
                    textView.setError(null);
//                textView.setText(charSequence);
            }
        });
    }

}
