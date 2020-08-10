package com.example.testmvp.ui.databinding;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.testmvp.R;
import com.example.testmvp.data.db.entity.News;
import com.example.testmvp.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends AppCompatActivity {

    //query data from db
    News news = new News(45L, "test", "message testing body", "asdasdasdasd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityDataBindingBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        setContentView(binding.getRoot());
        binding.setNewsEntity(news);

    }
}
