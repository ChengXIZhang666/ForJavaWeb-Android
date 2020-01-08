package com.example.forjavaweb.bean;

/**
 * @program: ForAndroid
 * @description: 居住服务信息Bean
 * @author: 张成灬玺
 * @create: 2020-01-04 14:55
 ***/
public class LiveBean {
    private int lId;
    private String lName;
    private String lTel;
    private String lType;
    private String lPrice;
    private String lLocate;

    public LiveBean(int lId, String lName, String lTel, String lType, String lPrice, String lLocate) {
        this.lId = lId;
        this.lName = lName;
        this.lTel = lTel;
        this.lType = lType;
        this.lPrice = lPrice;
        this.lLocate = lLocate;
    }

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getlTel() {
        return lTel;
    }

    public void setlTel(String lTel) {
        this.lTel = lTel;
    }

    public String getlType() {
        return lType;
    }

    public void setlType(String lType) {
        this.lType = lType;
    }

    public String getlPrice() {
        return lPrice;
    }

    public void setlPrice(String lPrice) {
        this.lPrice = lPrice;
    }

    public String getlLocate() {
        return lLocate;
    }

    public void setlLocate(String lLocate) {
        this.lLocate = lLocate;
    }
}
