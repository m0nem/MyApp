package com.example.testmvp.ui.contact_list;

import android.os.Bundle;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testmvp.R;
import com.example.testmvp.TestMvp;
import com.example.testmvp.data.db.database.AppDb;
import com.example.testmvp.data.db.entity.Contact;
import com.example.testmvp.databinding.ActivityContactBinding;
import com.example.testmvp.di.component.ContactComponent;
import com.example.testmvp.di.component.DaggerContactComponent;
import com.example.testmvp.di.module.ContactModule;
import com.example.testmvp.ui.base.BaseActivity;
import com.example.testmvp.utils.pojo.NetworkState;

public class ContactActivity extends BaseActivity implements IContact.IView {

    ActivityContactBinding binding;
    ContactRvAdapter adapter;
    ContactPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TestMvp.UsingApi = false;

        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact);
        setContentView(binding.getRoot());

        //dagger
        ContactComponent component = DaggerContactComponent.builder()
                .contactModule(new ContactModule(this, AppDb.getDatabase(this),this)).build();
        presenter = component.providePresenter();

        binding.setPresenter(presenter);

        presenter.listenForNetState(this);

        //rv
        binding.rvContact
                .setLayoutManager(new LinearLayoutManager(this,
                        RecyclerView.VERTICAL,
                        false));

        adapter = new ContactRvAdapter(this,presenter);

        binding.rvContact.setAdapter(adapter);

//        adapter.submitList(null);
//        adapter.notifyDataSetChanged();

        //livedata
        presenter
              //  .getCustomList()
                .getLiveDataList(this, 1)
                .observe(this, new Observer<PagedList<Contact>>() {
                    @Override
                    public void onChanged(PagedList<Contact> contacts) {
                        adapter.submitList(contacts);
                    }
                });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        presenter
//                .getCustomList()
//                .observe(this, new Observer<PagedList<Contact>>() {
//                    @Override
//                    public void onChanged(PagedList<Contact> contacts) {
//                        adapter.submitList(contacts);
//                    }
//                });
//
//    }

    @Override
    public void showState(NetworkState state) {

        Toast.makeText(this,state.name(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void afterDelete() {

        Toast.makeText(this,"Delete it !",Toast.LENGTH_LONG).show();


    }
}
