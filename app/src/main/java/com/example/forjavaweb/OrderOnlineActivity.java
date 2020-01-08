package com.example.forjavaweb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.example.forjavaweb.tool.LogUtil;
import com.example.forjavaweb.tool.ServerUtil;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OrderOnlineActivity extends AppCompatActivity implements View.OnClickListener {
    //    private static final String Eat = "http://172.18.179.70:8080/GetEatOrderServlet";
    //    private static final String Live = "http://172.18.179.70:8080/GetLiveOrderServlet";
    //    private static final String Take = "http://172.18.179.70:8080/GetTakeOrderServlet";

    //    private static final String Order = "http://172.18.179.70:8080/SelectServiceServlet";
    private static final String Order = ServerUtil.getSelectServiceServlet();
    private static final int Eat = 1;//异步同步信号w
    private static final int Live = 2;//异步同步信号
    private static final int Take = 3;//异步同步信号
    private Button getEat, getLive, getTake;
    private String sign;
    private String uId;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_online);
        //设置进入、退出动画
        getWindow().setEnterTransition(new Slide(Gravity.LEFT));
        getWindow().setExitTransition(new Slide(Gravity.LEFT));
        getEat = findViewById(R.id.get_eat_service);
        getLive = findViewById(R.id.get_live_service);
        getTake = findViewById(R.id.get_take_service);
        getEat.setOnClickListener(this);
        getLive.setOnClickListener(this);
        getTake.setOnClickListener(this);
        Intent intent = getIntent();
        uId = intent.getStringExtra("uId");
    }

    //更新fab
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_eat_service:
                sign = "Eat";
                toOrder(sign);
                break;
            case R.id.get_live_service:
                sign = "Live";
                toOrder(sign);
                break;
            case R.id.get_take_service:
                sign = "Take";
                toOrder(sign);
                break;
            default:
                break;
        }
    }

    private void toOrder(final String type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("message", type)
                            .build();
                    Request request = new Request.Builder()
//                    172.18.179.70
                            .url(Order)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    LogUtil.ShowText(getClass().getName(), responseData);
                    forOrder(responseData);
//                    if (!responseData.isEmpty()) {
//                        Message message = new Message();
//                        message.what = 1;
//                        handler.sendMessage(message);
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void forOrder(String responseData) {
        Intent intent = new Intent(OrderOnlineActivity.this, ShowOrderMessageActivity.class);
        intent.putExtra("data", responseData);
        intent.putExtra("type", sign);
        intent.putExtra("uId", uId);
        startActivity(intent);
    }
}
