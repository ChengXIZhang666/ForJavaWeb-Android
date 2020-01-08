package com.example.forjavaweb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.forjavaweb.ServiceIndentManage.EatIndentManageActivity;
import com.example.forjavaweb.ServiceIndentManage.LiveIndentManageActivity;
import com.example.forjavaweb.ServiceIndentManage.TakeIndentManageActivity;

public class ChooseIndentManageActivity extends AppCompatActivity implements View.OnClickListener {
    private Button choose_eat_indent, choose_live_indent, choose_take_indent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_indent_manage);
        choose_eat_indent = findViewById(R.id.choose_eat_indent);
        choose_live_indent = findViewById(R.id.choose_live_indent);
        choose_take_indent = findViewById(R.id.choose_take_indent);
        choose_take_indent.setOnClickListener(this);
        choose_eat_indent.setOnClickListener(this);
        choose_live_indent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_eat_indent:
                Intent choose_eat_intent = new Intent(ChooseIndentManageActivity.this, EatIndentManageActivity.class);
                startActivity(choose_eat_intent);
                break;
            case R.id.choose_live_indent:
                Intent choose_live_intent = new Intent(ChooseIndentManageActivity.this, LiveIndentManageActivity.class);
                startActivity(choose_live_intent);
                break;
            case R.id.choose_take_indent:
                Intent choose_take_intent = new Intent(ChooseIndentManageActivity.this, TakeIndentManageActivity.class);
                startActivity(choose_take_intent);
                break;
            default:
                break;
        }
    }
}
