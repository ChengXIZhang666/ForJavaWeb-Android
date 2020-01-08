package com.example.forjavaweb.ServiceIndentManage;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;

import com.example.forjavaweb.R;
import com.example.forjavaweb.adapter.EatIndentManageAdapter;
import com.example.forjavaweb.bean.EatIndentBean;
import com.example.forjavaweb.tool.JsonUtil;
import com.example.forjavaweb.tool.LogUtil;
import com.example.forjavaweb.tool.ServerUtil;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EatIndentManageActivity extends AppCompatActivity {
    private RecyclerView eat_indent_manage_recycler;
//    private Button delete_eat_indent, change_eat_indent;
    private List<EatIndentBean> eatIndentBeanList = new ArrayList<>();
    private String responseData;
    private int UPDATE_EAT = 1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat_indent_manage);

        //设置进入、退出动画
        getWindow().setEnterTransition(new Slide(Gravity.LEFT));
        getWindow().setExitTransition(new Slide(Gravity.LEFT));

        eat_indent_manage_recycler = findViewById(R.id.eat_indent_manage_recycler);
//        delete_eat_indent = findViewById(R.id.delete_eat_indent);
//        change_eat_indent = findViewById(R.id.change_eat_indent);

        //获取订单信息
        getEatIndent();
//        new TypeToken<List<NewsBean>>() {        }.getType()
    }

    //获取饮食订单信息
    private void getEatIndent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    RequestBody userlogin = new FormBody.Builder()
//                            .add("usernumber", usernumber.getText().toString())
//                            .add("userpassword", password.getText().toString())
//                            .build();
                    RequestBody getEatIndent = new FormBody.Builder()
                            .add("indent", "GetEat")
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
//                    172.18.179.70
//                            .url("http://172.18.179.70:8080/IndentServlet")
                            .url(ServerUtil.getIndentServlet())
                            .post(getEatIndent)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response != null) {
                        responseData = response.body().string();
                        Message message = new Message();
                        message.what = UPDATE_EAT;
                        handler.sendMessage(message);
                        LogUtil.ShowText(getClass().getName(), responseData);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == UPDATE_EAT) {
                setEats();
            }
        }
    };

    private void setEats() {
        eatIndentBeanList = JsonUtil.JsonToObject(responseData, new TypeToken<List<EatIndentBean>>() {
        }.getType());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        eat_indent_manage_recycler.setLayoutManager(linearLayoutManager);
        EatIndentManageAdapter eatIndentManageAdapter = new EatIndentManageAdapter(eatIndentBeanList);
        eat_indent_manage_recycler.setAdapter(eatIndentManageAdapter);
    }
}
