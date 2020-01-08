package com.example.forjavaweb.tool;

import android.content.Intent;

import com.example.forjavaweb.bean.EatBean;
import com.example.forjavaweb.bean.LiveBean;
import com.example.forjavaweb.bean.TakeBean;
import com.example.forjavaweb.bean.User;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ObjectUtil {
    public static User getUser(Intent intent) {
        String userFromServer = intent.getStringExtra("user");
        User user = JsonUtil.JsonToObject(userFromServer, User.class);
        return user;
    }

    public static EatBean getEat(Intent intent) {
        String eat = intent.getStringExtra("data");
        EatBean eatBean = JsonUtil.JsonToObject(eat, EatBean.class);
        return eatBean;
    }

    public static List<EatBean> getEatList(Intent intent) {
        String eatFromServer = intent.getStringExtra("eatmanage");
        List<EatBean> eatBeans = JsonUtil.JsonToObject(eatFromServer, new TypeToken<List<EatBean>>() {
        }.getType());
        return eatBeans;
    }

    public static List<LiveBean> getLiveList(Intent intent) {
        String LiveFromServer = intent.getStringExtra("live");
        List<LiveBean> liveBeans = JsonUtil.JsonToObject(LiveFromServer, new TypeToken<List<LiveBean>>() {
        }.getType());
        return liveBeans;
    }

    public static List<TakeBean> getTakeList(Intent intent) {
        String LiveFromServer = intent.getStringExtra("take");
        List<TakeBean> takeBeans = JsonUtil.JsonToObject(LiveFromServer, new TypeToken<List<TakeBean>>() {
        }.getType());
        return takeBeans;
    }

    public static List<Object> getList(Intent intent) {
        String dataFromServer = intent.getStringExtra("data");
        List<Object> Beans = JsonUtil.JsonToObject(dataFromServer, new TypeToken<List<Object>>() {
        }.getType());
        return Beans;
    }
}
