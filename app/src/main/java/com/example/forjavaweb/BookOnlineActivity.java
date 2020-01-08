package com.example.forjavaweb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.forjavaweb.bean.EatIndentBean;
import com.example.forjavaweb.bean.LiveIndentBean;
import com.example.forjavaweb.bean.TakeIndentBean;
import com.example.forjavaweb.tool.DateUtil;
import com.example.forjavaweb.tool.JsonUtil;
import com.example.forjavaweb.tool.LogUtil;
import com.example.forjavaweb.tool.ServerUtil;
import com.example.forjavaweb.tool.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BookOnlineActivity extends AppCompatActivity {
    //    private Spinner eatSpinner, liveSpinner, takeSpiner;
    private Button bookService, back2;
    private String eat, live, take, uId;
    private ViewPager viewPager;
    private List<View> pagerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_online);
//        eatSpinner = findViewById(R.id.book_eat);
//        liveSpinner = findViewById(R.id.book_live);
//        takeSpiner = findViewById(R.id.book_take);
//        eatmanage = eatSpinner.getSelectedItem().toString();
//        live = liveSpinner.getSelectedItem().toString();
//        take = takeSpiner.getSelectedItem().toString();
        Intent intent = getIntent();
        uId = intent.getStringExtra("uId");

        //设置ViewPager
        addView();
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return pagerList.size();//返回view的个数
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(pagerList.get(position));
                return pagerList.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(pagerList.get(position));
            }
        });
//        viewPager.setVisibility(View.GONE);

        back2 = findViewById(R.id.back2);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        bookService = findViewById(R.id.book_service);
//        bookService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    ToastUtil.sendToast(BookOnlineActivity.this, eatmanage + live + take);
//                if (eatmanage.equals("不预订此服务") && live.equals("不预订此服务") && take.equals("不预订此服务")) {
//                    ToastUtil.sendToast(BookOnlineActivity.this, "请先选择要预订的服务");
//                } else {
//                    viewPager.setVisibility(View.VISIBLE);
//                }
//            }
//        });
    }

    //把三个view即三个界面添加到List中
    void addView() {
        {
            View view = LayoutInflater.from(this).inflate(R.layout.eatindent, null);
//            ImageView imageView = view.findViewById(R.id.imageView);
//            imageView.setBackgroundColor(Color.RED);
            final Spinner eatType = view.findViewById(R.id.eat_type);
            final TextView eatPrice = view.findViewById(R.id.eat_price);
            final TextView eatDate = view.findViewById(R.id.eat_time);
            eatType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String[] eatString = getResources().getStringArray(R.array.eat);
                    if (eatString[i].equals("商务套餐"))
                        eatPrice.setText(30 + "元");
                    if (eatString[i].equals("外卖"))
                        eatPrice.setText(20 + "元");
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            eatDate.setText(DateUtil.getDate());
            Button eatSend = view.findViewById(R.id.eat_send);
            eatSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EatIndentBean eatIndentBean = new EatIndentBean();
                    eatIndentBean.setEsId(uId);
                    eatIndentBean.setEsType(eatType.getSelectedItem().toString());
//                    eatIndentBean.setEsDate(eatDate.getText().toString());
                    eatIndentBean.setEsDate(DateUtil.getDate());
                    eatIndentBean.setEsPrice(eatPrice.getText().toString());
                    toEat(eatIndentBean);
                    ToastUtil.sendToast(BookOnlineActivity.this, "饮食订单下单成功！");
                }
            });
            pagerList.add(view);
        }
        {
            View view = LayoutInflater.from(this).inflate(R.layout.liveindent, null);
//            ImageView imageView = view.findViewById(R.id.imageView);
//            imageView.setBackgroundColor(Color.GREEN);
            final Spinner liveType = view.findViewById(R.id.live_type);
            final TextView livePrice = view.findViewById(R.id.live_price);
            final Spinner liveInTime = view.findViewById(R.id.live_in_time);
            final Spinner liveOutTime = view.findViewById(R.id.live_out_time);
            liveType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String[] liveString = getResources().getStringArray(R.array.live);
                    if (liveString[i].equals("单人间"))
                        livePrice.setText(700 + "元");
                    if (liveString[i].equals("双人间"))
                        livePrice.setText(850 + "元");
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            final TextView liveDate = view.findViewById(R.id.live_time);
            liveDate.setText(DateUtil.getDate());
            Button liveSend = view.findViewById(R.id.live_send);
            liveSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LiveIndentBean liveIndentBean = new LiveIndentBean();
                    liveIndentBean.setLsId(uId);
                    liveIndentBean.setLsType(liveType.getSelectedItem().toString());
                    liveIndentBean.setLsPrice(livePrice.getText().toString());
                    liveIndentBean.setLsInTime(liveInTime.getSelectedItem().toString());
                    liveIndentBean.setLsOutTime(liveOutTime.getSelectedItem().toString());
//                    liveIndentBean.setLsDate(liveDate.getText().toString());
                    liveIndentBean.setLsDate(DateUtil.getDate());
                    toLive(liveIndentBean);
                    ToastUtil.sendToast(BookOnlineActivity.this, "居住订单下单成功！");
                }
            });
            pagerList.add(view);
        }
        {
            View view = LayoutInflater.from(this).inflate(R.layout.takeindent, null);
//            ImageView imageView = view.findViewById(R.id.imageView);
////            imageView.setBackgroundColor(Color.YELLOW);
            final Spinner takeLocate = view.findViewById(R.id.take_locate);
            final Spinner takePersonNumber = view.findViewById(R.id.take_person_number);
            final TextView takePrice = view.findViewById(R.id.take_price);
            final TextView takeCarNumber = view.findViewById(R.id.take_car_number);
            final Spinner takeOrder = view.findViewById(R.id.take_order);
            final TextView takeDate = view.findViewById(R.id.take_time);
            Button takeSend = view.findViewById(R.id.take_send);
            takePersonNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String[] takeString = getResources().getStringArray(R.array.number);
//                    if (takeString[i].equals("1"))
//                        takePrice.setText(3 + "元");
//                    if (takeString[i].equals("外卖"))
//                        takePrice.setText(20 + "元");
                    switch (takeString[i]) {
                        case "1":
                            takePrice.setText("5");
                            break;
                        case "2":
                            takePrice.setText("10");
                            break;
                        case "3":
                            takePrice.setText("15");
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            takeDate.setText(DateUtil.getDate());
            takeSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TakeIndentBean takeIndentBean = new TakeIndentBean();
                    takeIndentBean.setTsId(uId);
                    takeIndentBean.setTsLocate(takeLocate.getSelectedItem().toString());
                    takeIndentBean.setTsPersonNumber(takePersonNumber.getSelectedItem().toString());
                    takeIndentBean.setTsCarNumber(takeCarNumber.getText().toString());
                    takeIndentBean.setTsPrice(takePrice.getText().toString());
                    takeIndentBean.setTsOrder(takeOrder.getSelectedItem().toString());
//                    takeIndentBean.setTsDate(takeDate.getText().toString());
                    takeIndentBean.setTsDate(DateUtil.getDate());
                    toTake(takeIndentBean);
                    ToastUtil.sendToast(BookOnlineActivity.this, "接送订单下单成功！");
                }
            });
            pagerList.add(view);
        }
    }

    //eatmanage
    private void toEat(final EatIndentBean eatIndentBean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody eat = new FormBody.Builder()
                            .add("message", JsonUtil.ObjectToJson(eatIndentBean))
                            .add("indent", "Eat")
                            .build();
                    Request request = new Request.Builder()
//                    172.18.179.70
//                            .url("http://172.18.179.70:8080/IndentServlet")
                            .url(ServerUtil.getIndentServlet())
                            .post(eat)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response != null) {
                        String responseData = response.body().string();

                        LogUtil.ShowText(getClass().getName(), responseData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //live
    private void toLive(final LiveIndentBean liveIndentBean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody eat = new FormBody.Builder()
                            .add("message", JsonUtil.ObjectToJson(liveIndentBean))
                            .add("indent", "Live")
                            .build();
                    Request request = new Request.Builder()
//                    172.18.179.70
                            .url("http://172.18.179.70:8080/IndentServlet")
                            .post(eat)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response != null) {
                        String responseData = response.body().string();
                        LogUtil.ShowText(getClass().getName(), responseData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //take
    private void toTake(final TakeIndentBean takeIndentBean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody eat = new FormBody.Builder()
                            .add("message", JsonUtil.ObjectToJson(takeIndentBean))
                            .add("indent", "Take")
                            .build();
                    Request request = new Request.Builder()
//                    172.18.179.70
                            .url("http://172.18.179.70:8080/IndentServlet")
                            .post(eat)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response != null) {
                        String responseData = response.body().string();
                        LogUtil.ShowText(getClass().getName(), responseData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}