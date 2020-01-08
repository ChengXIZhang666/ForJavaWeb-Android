package com.example.forjavaweb.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.forjavaweb.R;
import com.example.forjavaweb.bean.LiveIndentBean;

import java.util.List;

public class LiveIndentManageAdapter extends RecyclerView.Adapter<LiveIndentManageAdapter.ViewHolder> {
    private List<LiveIndentBean> liveIndentBeanList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView live_indent_manage_type, live_indent_manage_in_time, live_indent_manage_out_time, live_indent_manage_price, live_indent_manage_time;
        Button live_indent_manage_send;

        public ViewHolder(View view) {
            super(view);
            live_indent_manage_type = view.findViewById(R.id.live_indent_manage_type);
            live_indent_manage_in_time = view.findViewById(R.id.live_indent_manage_in_time);
            live_indent_manage_out_time = view.findViewById(R.id.live_indent_manage_out_time);
            live_indent_manage_price = view.findViewById(R.id.live_indent_manage_price);
            live_indent_manage_time = view.findViewById(R.id.live_indent_manage_time);
            live_indent_manage_send = view.findViewById(R.id.live_indent_manage_send);
        }
    }

    public LiveIndentManageAdapter(List<LiveIndentBean> liveIndentBeanList) {
        this.liveIndentBeanList = liveIndentBeanList;
    }

    @NonNull
    @Override
    public LiveIndentManageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.liveindentmanage_item, viewGroup, false);
        LiveIndentManageAdapter.ViewHolder holder = new LiveIndentManageAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LiveIndentManageAdapter.ViewHolder viewHolder, int i) {
        LiveIndentBean liveIndentBean = liveIndentBeanList.get(i);
//        viewHolder.eat_indent_manage_type.setSelection(0, true);
//        viewHolder.eat_indent_manage_price.setText(eatIndentBean.getEsPrice());
//        viewHolder.eat_indent_manage_time.setText(eatIndentBean.getEsDate());
        viewHolder.live_indent_manage_type.setText(liveIndentBean.getLsType());
        viewHolder.live_indent_manage_in_time.setText(liveIndentBean.getLsInTime());
        viewHolder.live_indent_manage_out_time.setText(liveIndentBean.getLsOutTime());
        viewHolder.live_indent_manage_price.setText(liveIndentBean.getLsPrice());
        viewHolder.live_indent_manage_time.setText(liveIndentBean.getLsDate());
    }

    @Override
    public int getItemCount() {
        return liveIndentBeanList.size();
    }
}
