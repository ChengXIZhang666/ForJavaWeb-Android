package com.example.forjavaweb.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.forjavaweb.bean.LiveBean;

import java.util.List;

public class LiveAdapter extends ArrayAdapter<LiveBean> {
    private int resourceId;

    public LiveAdapter(@NonNull Context context, int resource, @NonNull List<LiveBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LiveBean liveBean = getItem(position);
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
//        TextView show1 = view.findViewById(R.id.show1);
//        TextView show2 = view.findViewById(R.id.show2);
//        TextView show3 = view.findViewById(R.id.show3);
//        TextView show4 = view.findViewById(R.id.show4);
//        TextView show5 = view.findViewById(R.id.show5);
//        TextView show6 = view.findViewById(R.id.show6);
//        List<TextView> textViewList = new ArrayList<>();
//        textViewList.add(show1);
//        textViewList.add(show2);
//        textViewList.add(show3);
//        textViewList.add(show4);
//        textViewList.add(show5);
//        textViewList.add(show6);
//        for (int i = 0; i < 2; i++) {
//        if (liveBeans != null) {
//            while (eatBeanList)
//            show1.setText("名称：" + liveBeans.get(0).getlName());
//            show1.setVisibility(View.VISIBLE);
//            show2.setText("联系方式：" + liveBeans.get(0).getlTel());
//            show2.setVisibility(View.VISIBLE);
//            show3.setText("类型：" + liveBeans.get(0).getlType());
//            show3.setVisibility(View.VISIBLE);
//            show4.setText("价格：" + liveBeans.get(0).getlPrice() + "元/晚/间");
//            show4.setVisibility(View.VISIBLE);
//            show5.setText("地址：" + liveBeans.get(0).getlLocate());
//            show5.setVisibility(View.VISIBLE);
//        }
//        }
//        show1.setText("名称：" + liveBean.getlName());
//        show1.setVisibility(View.VISIBLE);
//        show2.setText("联系方式：" + liveBean.getlTel());
//        show2.setVisibility(View.VISIBLE);
//        show3.setText("类型：" + liveBean.getlType());
//        show3.setVisibility(View.VISIBLE);
//        show4.setText("价格：" + liveBean.getlPrice() + "元/晚/间");
//        show4.setVisibility(View.VISIBLE);
//        show5.setText("地址：" + liveBean.getlLocate());
//        show5.setVisibility(View.VISIBLE);
        return view;
    }
}
