package com.example.forjavaweb;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.example.forjavaweb.bean.User;
import com.example.forjavaweb.tool.JsonUtil;
import com.example.forjavaweb.tool.LocationUtil;
import com.example.forjavaweb.tool.LogUtil;
import com.example.forjavaweb.tool.ServerUtil;
import com.example.forjavaweb.tool.ToastUtil;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText userid, username, userphone, userpassword, userprovince, usercity;
    private Button register;
    private TextView back;
    private int init = 0;
    private LocationClient locationClient = new LocationClient(RegisterActivity.this);
//    private TextView get_locate;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //设置进入、退出动画
        getWindow().setEnterTransition(new Slide(Gravity.LEFT));
        getWindow().setExitTransition(new Slide(Gravity.LEFT));
        userid = findViewById(R.id.get_userid);
        username = findViewById(R.id.get_username);
        userphone = findViewById(R.id.get_userphone);
        userpassword = findViewById(R.id.get_userpassword);
        userprovince = findViewById(R.id.get_userprovince);
        usercity = findViewById(R.id.get_usercity);
        back = findViewById(R.id.back);
        register = findViewById(R.id.register);
        back.setOnClickListener(this);
        register.setOnClickListener(this);
//        get_locate = findViewById(R.id.get_locate);
        LocationUtil locationUtil = new LocationUtil(locationClient, userprovince, usercity);
        locationUtil.getLocation();
    }

    //封装user
    public User getUserMessage() {
        User user = new User();
        user.setuId(userid.getText().toString());
        user.setuName(username.getText().toString());
        user.setuPhone(userphone.getText().toString());
        user.setuPassword(userpassword.getText().toString());
        user.setuProvince(userprovince.getText().toString());
        user.setuCity(usercity.getText().toString());
        user.setuCondition(init);
        user.setuEatService(init);
        user.setuLiveService(init);
        user.setuTakeService(init);
        return user;
    }

    //点击集合
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.register:
                toRegister(getUserMessage());
                ToastUtil.sendToast(RegisterActivity.this, "注册成功!");
                break;
            default:
                break;
        }
    }

    //发送注册信息到服务器
    public void toRegister(final User user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String ResponString = JsonUtil.ObjectToJson(user);
                    OkHttpClient client = new OkHttpClient();
                    RequestBody useregister = new FormBody.Builder()
//                            .add("uId", user.getuId())
//                            .add("uPhone", user.getuPhone())
//                            .add("uName", user.getuName())
//                            .add("uPassword", user.getuPassword())
//                            .add("uProvince", user.getuProvince())
//                            .add("uCity", user.getuCity())
//                            .add("uCondition", user.getuCondition())
//                            .add("uSerivce", user.getuService())
                            .add("RegisteMessage", ResponString)
                            .build();
                    Request request = new Request.Builder()
//                    172.18.179.70
//                            .url("http://172.18.179.70:8080/UserRegisterServlet")
                            .url(ServerUtil.getUserRegisterServlet())
                            .post(useregister)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    LogUtil.ShowText(getClass().getName(), responseData);
                    foregister(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //注册成功跳回登录界面
    private void foregister(String a) {
        if (a.equals("注册成功")) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.putExtra("sign", a);
            startActivity(intent);
            finish();
        }
    }

}
