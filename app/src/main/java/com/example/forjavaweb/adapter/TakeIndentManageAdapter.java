package com.example.forjavaweb.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.forjavaweb.R;
import com.example.forjavaweb.bean.TakeIndentBean;

import java.util.List;

public class TakeIndentManageAdapter extends RecyclerView.Adapter<TakeIndentManageAdapter.ViewHolder> {
    private List<TakeIndentBean> takeIndentBeanList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView take_indent_manage_locate, take_indent_manage_person_number, take_indent_manage_order, take_indent_manage_price, take_indent_manage_car_number, take_indent_manage_time;
        Button take_indent_manage_send;

        public ViewHolder(View view) {
            super(view);
            take_indent_manage_locate = view.findViewById(R.id.take_indent_manage_locate);
            take_indent_manage_person_number = view.findViewById(R.id.take_indent_manage_person_number);
            take_indent_manage_order = view.findViewById(R.id.take_indent_manage_order);
            take_indent_manage_price = view.findViewById(R.id.take_indent_manage_price);
            take_indent_manage_car_number = view.findViewById(R.id.take_indent_manage_car_number);
            take_indent_manage_time = view.findViewById(R.id.take_indent_manage_time);
            take_indent_manage_send = view.findViewById(R.id.take_indent_manage_send);
        }
    }

    public TakeIndentManageAdapter(List<TakeIndentBean> takeIndentBeanList) {
        this.takeIndentBeanList = takeIndentBeanList;
    }

    @NonNull
    @Override
    public TakeIndentManageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.takeindentmanage_item, viewGroup, false);
        TakeIndentManageAdapter.ViewHolder holder = new TakeIndentManageAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TakeIndentManageAdapter.ViewHolder viewHolder, int i) {
        TakeIndentBean takeIndentBean = takeIndentBeanList.get(i);
//        viewHolder.eat_indent_manage_type.setSelection(0, true);
//        viewHolder.eat_indent_manage_price.setText(eatIndentBean.getEsPrice());
//        viewHolder.eat_indent_manage_time.setText(eatIndentBean.getEsDate());
        viewHolder.take_indent_manage_car_number.setText(takeIndentBean.getTsCarNumber());
        viewHolder.take_indent_manage_locate.setText(takeIndentBean.getTsLocate());
        viewHolder.take_indent_manage_order.setText(takeIndentBean.getTsOrder());
        viewHolder.take_indent_manage_person_number.setText(takeIndentBean.getTsPersonNumber());
        viewHolder.take_indent_manage_price.setText(takeIndentBean.getTsPrice());
        viewHolder.take_indent_manage_time.setText(takeIndentBean.getTsDate());
    }

    @Override
    public int getItemCount() {
        return takeIndentBeanList.size();
    }
}
