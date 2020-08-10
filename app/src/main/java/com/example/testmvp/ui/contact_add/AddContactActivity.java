package com.example.testmvp.ui.contact_add;

import android.os.Bundle;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import com.example.testmvp.R;
import com.example.testmvp.data.db.database.AppDb;
import com.example.testmvp.data.db.entity.Contact;
import com.example.testmvp.databinding.ActivityAddContactBinding;
import com.example.testmvp.ui.base.BaseActivity;

public class AddContactActivity extends BaseActivity implements IAddContact.IView{

    ActivityAddContactBinding binding;
    Contact model = new Contact();
    AddContactPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       binding  =
                DataBindingUtil.setContentView(this, R.layout.activity_add_contact);
        setContentView(binding.getRoot());
        binding.setAddContactEntity(model);

        presenter=new AddContactPresenter(AppDb.getDatabase(this),this);

        binding.setPresenter(presenter);

    }

    @Override
    public void afterInsert(boolean isSuccess) {

        if(isSuccess) {
            Toast.makeText(this, "]", Toast.LENGTH_LONG).show();
           // finish();

        }else{
            Toast.makeText(getApplicationContext(),"UnSuccess",Toast.LENGTH_LONG).show();

        }
    }


}
