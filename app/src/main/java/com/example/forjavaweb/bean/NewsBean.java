package com.example.forjavaweb.bean;

public class NewsBean {
    private int nId;
    private String nAuthor;
    private String nTitle;
    private String nContent;
    private String nData;
    private String nCondition;

    public NewsBean(int nId, String nAuthor, String nTitle, String nContent, String nData, String nCondition) {
        this.nId = nId;
        this.nAuthor = nAuthor;
        this.nTitle = nTitle;
        this.nContent = nContent;
        this.nData = nData;
        this.nCondition = nCondition;
    }

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }

    public String getnAuthor() {
        return nAuthor;
    }

    public void setnAuthor(String nAuthor) {
        this.nAuthor = nAuthor;
    }

    public String getnTitle() {
        return nTitle;
    }

    public void setnTitle(String nTitle) {
        this.nTitle = nTitle;
    }

    public String getnContent() {
        return nContent;
    }

    public void setnContent(String nContent) {
        this.nContent = nContent;
    }

    public String getnData() {
        return nData;
    }

    public void setnData(String nData) {
        this.nData = nData;
    }

    public String getnCondition() {
        return nCondition;
    }

    public void setnCondition(String nCondition) {
        this.nCondition = nCondition;
    }
}
