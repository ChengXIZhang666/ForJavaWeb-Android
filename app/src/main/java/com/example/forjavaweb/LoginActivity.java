package com.example.forjavaweb;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.forjavaweb.bean.User;
import com.example.forjavaweb.tool.JsonUtil;
import com.example.forjavaweb.tool.LogUtil;
import com.example.forjavaweb.tool.ServerUtil;
import com.example.forjavaweb.tool.ToastUtil;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernumber, password;
    private Button login;
    private TextView visitor, register;
    private String sign, uId = "游客";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //设置进入、退出动画
        getWindow().setEnterTransition(new Slide(Gravity.LEFT));
        getWindow().setExitTransition(new Slide(Gravity.LEFT));

        Intent intent = getIntent();
        sign = intent.getStringExtra("sign");
        if (sign != null)
            ToastUtil.sendToast(LoginActivity.this, sign);
        usernumber = findViewById(R.id.usernumber);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        visitor = findViewById(R.id.visitor);
        register = findViewById(R.id.register);

        login.setOnClickListener(this);
        visitor.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void toLogin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder()
//                            .url("https://www.baidu.com")
//                            .build();
                    RequestBody userlogin = new FormBody.Builder()
                            .add("usernumber", usernumber.getText().toString())
                            .add("userpassword", password.getText().toString())
                            .build();
                    Request request = new Request.Builder()
//                    172.18.179.70
//                            .url("http://172.18.179.70:8080/UserLoginServlet")
                            .url(ServerUtil.getUserLoginServlet())
                            .post(userlogin)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    LogUtil.ShowText(getClass().getName(), responseData);
                    forlogin(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void forlogin(String object) {
        if (!object.isEmpty()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("user", object);
            startActivity(intent);
            User user = JsonUtil.JsonToObject(object, User.class);
            showNotice(user.getuName());
        }
    }

    public void showNotice(String content) {
        String id = "channel_001";
        String name = "name";
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//判断API
            @SuppressLint("WrongConstant") NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_MAX);
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(this)
                    .setChannelId(id)
                    .setContentTitle("登录成功")
                    .setContentText("欢迎" + content + "登录！")
                    .setSmallIcon(R.drawable.tx)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.tx))
                    .setAutoCancel(true)
                    .build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle("登录成功")
                    .setContentText("欢迎" + content + "登录")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setOngoing(true)
                    .setChannelId(id)
                    .setAutoCancel(true);
            notification = notificationBuilder.build();
        }

        notificationManager.notify(1, notification);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                toLogin();
                break;
            case R.id.visitor:
                Intent visitorintent = new Intent(LoginActivity.this, MainActivity.class);
                visitorintent.putExtra("sign", "游客登录");
                startActivity(visitorintent);
                showNotice("游客");
                break;
            case R.id.register:
                Intent registerintent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerintent);
                break;
            default:
                break;
        }
    }
}
