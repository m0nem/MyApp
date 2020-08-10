package com.example.testmvp.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testmvp.data.db.entity.News;
import com.example.testmvp.databinding.NewsRvItemBinding;

public class NewsRvAdapter extends PagedListAdapter<News, NewsRvAdapter.Holder> {

    private Context context;
    private LayoutInflater inflater;
    String TAG = "NewsRvAdapter";


    public NewsRvAdapter() {
        super(diff);

    }

    protected NewsRvAdapter(Context context) {
        super(diff);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsRvItemBinding newsRvItemBinding = NewsRvItemBinding.inflate(inflater);
        return new NewsRvAdapter.Holder(newsRvItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        News model = this.getItem(position);
        holder.bind(model);
    }

    class Holder extends RecyclerView.ViewHolder {

        private NewsRvItemBinding binder;

        public Holder(NewsRvItemBinding newsRvItemBinding) {
            super(newsRvItemBinding.getRoot());
            binder = newsRvItemBinding;
        }

        public void bind(News news) {
            binder.tvId.setText(news.getNewsID() + "");
            binder.tvTitle.setText(news.getTitle());
            binder.tvBody.setText(news.getBody());
            binder.tvDate.setText(news.getDate());
        }

    }

    //controll for list datas
    public static final DiffUtil.ItemCallback<News> diff = new DiffUtil.ItemCallback<News>() {
        @Override
        public boolean areItemsTheSame(@NonNull News oldItem, @NonNull News newItem) {
            return oldItem.getNewsID() == newItem.getNewsID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull News oldItem, @NonNull News newItem) {
            return oldItem.equals(newItem);
        }
    };
}
