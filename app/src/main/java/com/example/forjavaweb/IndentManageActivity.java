package com.example.forjavaweb;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import com.example.forjavaweb.bean.EatIndentBean;

import java.util.ArrayList;
import java.util.List;

public class IndentManageActivity extends AppCompatActivity {
    private List<EatIndentBean> eatIndentBeanList = new ArrayList<>();
    private List<View> pagerList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent_manage);
        //设置进入、退出动画
        getWindow().setEnterTransition(new Slide(Gravity.LEFT));
        getWindow().setExitTransition(new Slide(Gravity.LEFT));


    }
}
