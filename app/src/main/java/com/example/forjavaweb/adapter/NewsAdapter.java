package com.example.forjavaweb.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.forjavaweb.R;
import com.example.forjavaweb.bean.NewsBean;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsBean> newsBeans;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle, newsAuthor, newsContent, newsData;

        public ViewHolder(View view) {
            super(view);
            newsTitle = view.findViewById(R.id.news_title);
            newsAuthor = view.findViewById(R.id.news_author);
            newsContent = view.findViewById(R.id.news_content);
            newsData = view.findViewById(R.id.news_data);
        }
    }

    public NewsAdapter(List<NewsBean> newsBeanList) {
        newsBeans = newsBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NewsBean newsBean = newsBeans.get(i);
        viewHolder.newsTitle.setText(newsBean.getnTitle());
        viewHolder.newsAuthor.setText(newsBean.getnAuthor());
        viewHolder.newsContent.setText(newsBean.getnContent());
        viewHolder.newsData.setText(newsBean.getnData());
    }

    @Override
    public int getItemCount() {
        return newsBeans.size();
    }
}
