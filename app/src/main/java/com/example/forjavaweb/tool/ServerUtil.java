package com.example.forjavaweb.tool;

public class ServerUtil {
    private static final String IndentServlet = "IndentServlet";
    private static final String UserApplyServlet = "UserApplyServlet";
    private static final String UserLoginServlet = "UserLoginServlet";
    private static final String NewsServlet = "NewsServlet";
    private static final String SelectServiceServlet = "SelectServiceServlet";
    private static final String UserRegisterServlet = "UserRegisterServlet";
//    public static final String IndentServlet = "IndentServlet";

    public static String getIndentServlet() {
        return getPort() + IndentServlet;
    }

    public static String getUserRegisterServlet() {
        return getPort() + UserRegisterServlet;
    }

    public static String getSelectServiceServlet() {
        return getPort() + SelectServiceServlet;
    }

    public static String getNewsServlet() {
        return getPort() + NewsServlet;
    }

    public static String getUserApplyServlet() {
        return getPort() + UserApplyServlet;
    }

    public static String getUserLoginServlet() {
        return getPort() + UserLoginServlet;
    }

    private static String getPort() {
        return getIp() + ":8080/";
    }

    private static String getIp() {
//        return "http://172.18.179.70";//孵化基地IP地址
        return "http://192.168.43.86";//手机热点IP地址
//        return "http://";//手机热点IP地址
    }
}
