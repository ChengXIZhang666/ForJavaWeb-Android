package com.example.forjavaweb.bean;

/**
 * @program: ForAndroid
 * @description: 饮食服务信息Bean
 * @author: 张成灬玺
 * @create: 2020-01-04 14:54
 ***/
public class EatBean {
    private int eId;
    private String eType;

    public EatBean(int eId, String eType, String ePrice) {
        this.eId = eId;
        this.eType = eType;
        this.ePrice = ePrice;
    }

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    public String geteType() {
        return eType;
    }

    public void seteType(String eType) {
        this.eType = eType;
    }

    public String getePrice() {
        return ePrice;
    }

    public void setePrice(String ePrice) {
        this.ePrice = ePrice;
    }

    private String ePrice;
}
