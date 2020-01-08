package com.example.forjavaweb.tool;


/**
 * @program: ForAndroid
 * @description: 用来输出下面某些关键信息
 * @author: 张成灬玺
 * @create: 2019-12-09 21:07
 ***/
public class LogUtil {
    public static void ShowText(String from, String describe) {
        System.out.println("来自" + from + "的" + describe);
    }

    public static void ShowText(String from, boolean describe) {
        System.out.println("来自" + from + "的" + describe);
    }

    public static void ShowText(String from, int describe) {
        System.out.println("来自" + from + "的" + describe);
    }
}
