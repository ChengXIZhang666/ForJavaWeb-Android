package com.example.forjavaweb.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.forjavaweb.bean.EatIndentBean;

import java.util.List;

public class EatAdapter extends ArrayAdapter<EatIndentBean> {
    private int resourceId;

    public EatAdapter(@NonNull Context context, int resource, @NonNull List<EatIndentBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        EatIndentBean EatIndentBean = getItem(position);
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
//        if (EatIndentBeanList != null) {
//        show1.setText(EatIndentBeanList.get(0).geteType());
//        show1.setVisibility(View.VISIBLE);
//        show2.setText(EatIndentBeanList.get(0).getePrice());
//        show2.setVisibility(View.VISIBLE);
//        }
//        }
        return view;
    }

//    private void getEatList(List<EatIndentBean> EatIndentBeans) {
//        this.EatIndentBeanList = EatIndentBeans;
//    }
}
