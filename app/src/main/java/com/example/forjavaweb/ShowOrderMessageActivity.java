package com.example.forjavaweb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.forjavaweb.bean.EatBean;
import com.example.forjavaweb.bean.LiveBean;
import com.example.forjavaweb.bean.TakeBean;
import com.example.forjavaweb.tool.JsonUtil;
import com.example.forjavaweb.tool.LogUtil;
import com.example.forjavaweb.tool.ServerUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShowOrderMessageActivity extends AppCompatActivity {
    //    private List<EatBean> eatBeanList = new ArrayList<>();
//    private List<LiveBean> liveBeanList = new ArrayList<>();
//    private List<TakeBean> takeBeanList = new ArrayList<>();
//    private ListView listViewEat, listViewLive, listViewTake;
    private String sign;
    private TextView show1, show2, show3, show4, show5, show6;
    private List<TextView> textViewList = new ArrayList<>();
    private String uId;
    private Button orderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order_message);
        Intent intent = getIntent();
        uId = intent.getStringExtra("uId");
        sign = intent.getStringExtra("type");
        orderService = findViewById(R.id.order_service);
        show1 = findViewById(R.id.show1);
        show2 = findViewById(R.id.show2);
        show3 = findViewById(R.id.show3);
        show4 = findViewById(R.id.show4);
        show5 = findViewById(R.id.show5);
        show6 = findViewById(R.id.show6);
        textViewList.add(show1);
        textViewList.add(show2);
        textViewList.add(show3);
        textViewList.add(show4);
        textViewList.add(show5);
        textViewList.add(show6);
        for (int i = 0; i < textViewList.size(); i++) {
            textViewList.get(i).setTextSize(25);
        }
        setView(intent, sign);
        orderService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOrder(sign);
            }
        });
    }

    private void setView(Intent intent, String sign) {
        switch (sign) {
            case "Eat":
                LogUtil.ShowText("吃", intent.getStringExtra("data"));
                EatBean eatBean = JsonUtil.JsonToObject(intent.getStringExtra("data"), EatBean.class);
//                EatBean eatBean = ObjectUtil.getEat(intent);
                textViewList.get(0).setText("类型：" + eatBean.geteType());
                textViewList.get(0).setVisibility(View.VISIBLE);
                textViewList.get(1).setText("价格：" + eatBean.getePrice() + "元/人");
                textViewList.get(1).setVisibility(View.VISIBLE);

//                eatBeanList = ObjectUtil.getEatList(intent);
//                initEatBeans(eatBeanList);
//                EatAdapter eatAdapter = new EatAdapter(ShowOrderMessageActivity.this, R.layout.order_item, eatBeanList);
//                listViewEat = findViewById(R.id.list_view_eat);
//                listViewEat.setAdapter(eatAdapter);
                break;
            case "Live":
                LiveBean liveBean = JsonUtil.JsonToObject(intent.getStringExtra("data"), LiveBean.class);
                textViewList.get(2).setText("价格：" + liveBean.getlType());
                textViewList.get(0).setVisibility(View.VISIBLE);
                textViewList.get(1).setText("电话：" + liveBean.getlTel());
                textViewList.get(1).setVisibility(View.VISIBLE);
                textViewList.get(0).setText("名称：" + liveBean.getlName());
                textViewList.get(2).setVisibility(View.VISIBLE);
                textViewList.get(3).setText("价格：" + liveBean.getlPrice() + "晚/间");
                textViewList.get(3).setVisibility(View.VISIBLE);
                textViewList.get(4).setText("地址：" + liveBean.getlLocate());
                textViewList.get(4).setVisibility(View.VISIBLE);
//                liveBeanList = ObjectUtil.getLiveList(intent);
//                initLiveBeans(liveBeanList);
//                LiveAdapter liveAdapter = new LiveAdapter(ShowOrderMessageActivity.this, R.layout.order_item, liveBeanList);
//                listViewLive = findViewById(R.id.list_view_live);
//                listViewLive.setAdapter(liveAdapter);
                break;
            case "Take":
                TakeBean takeBean = JsonUtil.JsonToObject(intent.getStringExtra("data"), TakeBean.class);
                textViewList.get(0).setText("车牌号：" + takeBean.gettNum());
                textViewList.get(0).setVisibility(View.VISIBLE);
                textViewList.get(1).setText("日期：" + takeBean.gettDate());
                textViewList.get(1).setVisibility(View.VISIBLE);
                textViewList.get(2).setText("电话：" + takeBean.gettTel());
                textViewList.get(2).setVisibility(View.VISIBLE);
                textViewList.get(3).setText("班次：" + takeBean.gettOrder());
                textViewList.get(3).setVisibility(View.VISIBLE);
                textViewList.get(4).setText("接送站：" + takeBean.gettLocate());
                textViewList.get(4).setVisibility(View.VISIBLE);
                textViewList.get(5).setText("价格：" + takeBean.gettPrice() + "元/人");
                textViewList.get(5).setVisibility(View.VISIBLE);
//                takeBeanList = ObjectUtil.getTakeList(intent);
//                initTakeBeans(takeBeanList);
//                TakeAdapter takeAdapter = new TakeAdapter(ShowOrderMessageActivity.this, R.layout.order_item, takeBeanList);
//                listViewTake = findViewById(R.id.list_view_take);
//                listViewTake.setAdapter(takeAdapter);
                break;
            default:
                break;
        }
        //    private void initEatBeans() {
//        for (int i = 0; i < 4; i++) {
//            EatBean eatBean1 = new EatBean(eatBeanList.get(i))
//        }
//    }
//    private void initBeans() {
//
//    }
//    private void setListView(Intent intent, String sign) {
//        switch (sign) {
//            case "Eat":
//                eatBeanList = ObjectUtil.getEatList(intent);
////                initEatBeans(eatBeanList);
//                EatAdapter eatAdapter = new EatAdapter(ShowOrderMessageActivity.this, R.layout.order_item, eatBeanList);
//                listViewEat = findViewById(R.id.list_view_eat);
//                listViewEat.setAdapter(eatAdapter);
////                break;
//            case "Live":
//                liveBeanList = ObjectUtil.getLiveList(intent);
////                initLiveBeans(liveBeanList);
//                LiveAdapter liveAdapter = new LiveAdapter(ShowOrderMessageActivity.this, R.layout.order_item, liveBeanList);
//                listViewLive = findViewById(R.id.list_view_live);
//                listViewLive.setAdapter(liveAdapter);
////                break;
//            case "Take":
//                takeBeanList = ObjectUtil.getTakeList(intent);
////                initTakeBeans(takeBeanList);
//                TakeAdapter takeAdapter = new TakeAdapter(ShowOrderMessageActivity.this, R.layout.order_item, takeBeanList);
//                listViewTake = findViewById(R.id.list_view_take);
//                listViewTake.setAdapter(takeAdapter);
////                break;
//            default:
//                break;
//        }
//    }
//
//    private void initEatBeans(List<EatBean> eatBeans) {
//        for (int i = 0; i < 1; i++) {
//            EatBean eatBean = new EatBean(eatBeans.get(i).geteId(), eatBeans.get(i).geteType(), eatBeans.get(i).getePrice());
//            eatBeanList.add(eatBean);
//        }
//    }
//
//    private void initLiveBeans(List<LiveBean> liveBeans) {
//
//    }
//
//    private void initTakeBeans(List<TakeBean> takeBeans) {
//
//    }
    }

    private void getOrder(final String type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody orderservice = new FormBody.Builder()
                            .add("uId", uId)
                            .add("message", type)
                            .build();
                    Request request = new Request.Builder()
//                    172.18.179.70
//                            .url("http://172.18.179.70:8080/SelectServiceServlet")
                            .url(ServerUtil.getSelectServiceServlet())
                            .post(orderservice)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response != null) {
                        String responseData = response.body().string();
                        LogUtil.ShowText(getClass().getName(), responseData);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}