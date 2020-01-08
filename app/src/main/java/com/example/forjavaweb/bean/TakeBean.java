package com.example.forjavaweb.bean;

/**
 * @program: ForAndroid
 * @description: 接送服务信息Bean
 * @author: 张成灬玺
 * @create: 2020-01-04 14:58
 ***/
public class TakeBean {
    private int tId;
    private String tDate;
    private String tLocate;
    private String tOrder;//班次
    private String tPrice;
    private String tTel;
    private String tNum;//车牌号

    public TakeBean(int tId, String tDate, String tLocate, String tOrder, String tPrice, String tTel, String tNum) {
        this.tId = tId;
        this.tDate = tDate;
        this.tLocate = tLocate;
        this.tOrder = tOrder;
        this.tPrice = tPrice;
        this.tTel = tTel;
        this.tNum = tNum;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String gettDate() {
        return tDate;
    }

    public void settDate(String tDate) {
        this.tDate = tDate;
    }

    public String gettLocate() {
        return tLocate;
    }

    public void settLocate(String tLocate) {
        this.tLocate = tLocate;
    }

    public String gettOrder() {
        return tOrder;
    }

    public void settOrder(String tOrder) {
        this.tOrder = tOrder;
    }

    public String gettPrice() {
        return tPrice;
    }

    public void settPrice(String tPrice) {
        this.tPrice = tPrice;
    }

    public String gettTel() {
        return tTel;
    }

    public void settTel(String tTel) {
        this.tTel = tTel;
    }

    public String gettNum() {
        return tNum;
    }

    public void settNum(String tNum) {
        this.tNum = tNum;
    }
}
