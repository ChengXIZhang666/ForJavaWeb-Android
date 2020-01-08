package com.example.forjavaweb;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

import com.example.forjavaweb.tool.LogUtil;
import com.example.forjavaweb.tool.ToastUtil;

import java.io.File;

public class PaperManageActivity extends AppCompatActivity {
    private EditText get_paperName, get_paperAuthor;
    private Button post_paper, choose_paper;
    private Uri uri = null;
    private File file = null;
    private TextView paper_name;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_manage);
        //设置进入、退出动画
        getWindow().setEnterTransition(new Slide(Gravity.LEFT));
        getWindow().setExitTransition(new Slide(Gravity.LEFT));

        get_paperName = findViewById(R.id.get_paperName);
        get_paperAuthor = findViewById(R.id.get_paperAuthor);
        choose_paper = findViewById(R.id.choose_paper);
        paper_name = findViewById(R.id.paper_name);

        //提交论文
        choose_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSystemFile();
                ToastUtil.sendToast(PaperManageActivity.this,"论文上传失败！");
            }
        });
    }

    //打开文件选择器
    public void openSystemFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);//意图：文件浏览器
        intent.setType("*/*");//无类型限制
        intent.addCategory(Intent.CATEGORY_OPENABLE);//系统自带的文件浏览器
        startActivityForResult(intent, 1);
    }

    // 选择完文件后，自动调用，获取返回值uri
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (data.getData() != null) {
                uri = data.getData();
                LogUtil.ShowText("文件调用", uri.toString());
                //String path = getPath(getApplicationContext(),uri);
                if (uri != null) {
                    file = new File(uri.getPath());
                    if (file != null)
                        paper_name.setText(file.getName());
//                    try {
//                        FileUpload.uploadFile("http://172.18.179.70:8080/PaperReceviceServlet", file);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }
    }
}
