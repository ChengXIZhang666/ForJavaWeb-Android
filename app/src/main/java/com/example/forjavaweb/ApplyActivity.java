package com.example.forjavaweb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.forjavaweb.bean.UserApplyBean;
import com.example.forjavaweb.tool.DateUtil;
import com.example.forjavaweb.tool.JsonUtil;
import com.example.forjavaweb.tool.LogUtil;
import com.example.forjavaweb.tool.ServerUtil;
import com.example.forjavaweb.tool.ToastUtil;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApplyActivity extends AppCompatActivity {
    private EditText get_apply_name;
    private Spinner get_apply_order_time;
    private Button send_apply;
    private String responseData;
    private UserApplyBean userApplyBean = new UserApplyBean();
    private int UPDATE_TEXT = 1;
    private String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        Intent intent = getIntent();
        uId = intent.getStringExtra("user");
//        System.out.println(uId);
        get_apply_name = findViewById(R.id.get_Apply_Name);
        get_apply_order_time = findViewById(R.id.get_Apply_Order);
        send_apply = findViewById(R.id.send_apply);


        send_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toApply();
            }
        });
    }

    //报名
    private void toApply() {
        userApplyBean.setuId(uId);
        userApplyBean.setaName(get_apply_name.getText().toString());
        userApplyBean.setaOrder(get_apply_order_time.getSelectedItem().toString());
        userApplyBean.setaDate(DateUtil.getDate());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody userapply = new FormBody.Builder()
//                            .add("uId", user.getuId())
                            .add("apply", JsonUtil.ObjectToJson(userApplyBean))
                            .build();
                    Request request = new Request.Builder()
//                    172.18.179.70
//                            .url("http://172.18.179.70:8080/UserApplyServlet")
                            .url(ServerUtil.getUserApplyServlet())
                            .post(userapply)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response != null) {
                        responseData = response.body().string();
                        LogUtil.ShowText(getClass().getName(), responseData);
                        if (!responseData.isEmpty()) {
                            Message message = new Message();
                            message.what = UPDATE_TEXT;
                            handler.sendMessage(message);
                        }
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
            if (msg.what == UPDATE_TEXT) {
                ToastUtil.sendToast(ApplyActivity.this, "报名成功");
                Intent intent = new Intent();
                intent.putExtra("return", "1");
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    };
}
