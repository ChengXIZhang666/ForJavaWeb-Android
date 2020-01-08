package com.example.forjavaweb.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.forjavaweb.R;
import com.example.forjavaweb.bean.EatIndentBean;

import java.util.List;

public class EatIndentManageAdapter extends RecyclerView.Adapter<EatIndentManageAdapter.ViewHolder> {
    private List<EatIndentBean> eatIndentBeanList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView eat_indent_manage_type, eat_indent_manage_price, eat_indent_manage_time;
        Button change_eat_indent;

        public ViewHolder(View view) {
            super(view);
            eat_indent_manage_type = view.findViewById(R.id.eat_indent_manage_type);
            eat_indent_manage_price = view.findViewById(R.id.eat_indent_manage_price);
            eat_indent_manage_time = view.findViewById(R.id.eat_indent_manage_time);
            change_eat_indent = view.findViewById(R.id.change_eat_indent);
        }
    }

    public EatIndentManageAdapter(List<EatIndentBean> eatIndentBeanList) {
        this.eatIndentBeanList = eatIndentBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.eatindentmanage_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
//        holder.change_eat_indent.setOnClickListener(new View.OnClickListener() {
//            EatIndentBean eatIndentBean = eatIndentBeanList.get(i);
//            String id = valueOf(eatIndentBean.getEsId2());
//
//            @Override
//            public void onClick(View view) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
////                    RequestBody userlogin = new FormBody.Builder()
////                            .add("usernumber", usernumber.getText().toString())
////                            .add("userpassword", password.getText().toString())
////                            .build();
//                            RequestBody getTakeIndent = new FormBody.Builder()
//                                    .add("id", id)
//                                    .add("indent", "DeleteEat")
//                                    .build();
//                            OkHttpClient client = new OkHttpClient();
//                            Request request = new Request.Builder()
////                    172.18.179.70
//                                    .url("http://172.18.179.70:8080/IndentServlet")
//                                    .post(getTakeIndent)
//                                    .build();
//                            Response response = client.newCall(request).execute();
//                            if (response != null) {
//                                String responseData = response.body().string();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        EatIndentBean eatIndentBean = eatIndentBeanList.get(i);
        viewHolder.eat_indent_manage_type.setText(eatIndentBean.getEsType());
        viewHolder.eat_indent_manage_price.setText(eatIndentBean.getEsPrice());
        viewHolder.eat_indent_manage_time.setText(eatIndentBean.getEsDate());
    }

    @Override
    public int getItemCount() {
        return eatIndentBeanList.size();
    }
}
