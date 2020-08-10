package com.example.testmvp.ui.contact_list;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testmvp.data.db.database.AppDb;
import com.example.testmvp.data.db.entity.Contact;
import com.example.testmvp.databinding.ContactRvItemBinding;

public class ContactRvAdapter extends PagedListAdapter<Contact,ContactRvAdapter.Holder> {

    Context context;
    LayoutInflater inflater;
    ContactPresenter presenter;

    public ContactRvAdapter() {
        super(diff);
    }

    public ContactRvAdapter(Context context , ContactPresenter presenter){
        super(diff);
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.presenter = presenter;

    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new Holder(ContactRvItemBinding.inflate(inflater));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Contact model =this.getItem(position);

        holder.binding.setContactEntity(model);
        holder.binding.setPresenter(presenter);


    }

    class Holder extends RecyclerView.ViewHolder{

        ContactRvItemBinding binding;

        public Holder(ContactRvItemBinding item) {
            super(item.getRoot());
            binding = item;
        }


    }

    //controll for list datas
    public static final DiffUtil.ItemCallback<Contact> diff = new DiffUtil.ItemCallback<Contact>() {
        @Override
        public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.getConID() == newItem.getConID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.equals(newItem);
        }
    };
}
