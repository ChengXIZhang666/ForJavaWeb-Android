package com.example.forjavaweb.bean;

public class User {
    private String uId;
    private String uPhone;
    private String uName;
    private String uPassword;
    private String uProvince;
    private String uCity;
    private int uCondition;
    private int uEatService;
    private int uLiveService;
    private int uTakeService;

    public int getuCondition() {
        return uCondition;
    }

    public void setuCondition(int uCondition) {
        this.uCondition = uCondition;
    }

    public int getuEatService() {
        return uEatService;
    }

    public void setuEatService(int uEatService) {
        this.uEatService = uEatService;
    }

    public int getuLiveService() {
        return uLiveService;
    }

    public void setuLiveService(int uLiveService) {
        this.uLiveService = uLiveService;
    }

    public int getuTakeService() {
        return uTakeService;
    }

    public void setuTakeService(int uTakeService) {
        this.uTakeService = uTakeService;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuProvince() {
        return uProvince;
    }

    public void setuProvince(String uProvince) {
        this.uProvince = uProvince;
    }

    public String getuCity() {
        return uCity;
    }

    public void setuCity(String uCity) {
        this.uCity = uCity;
    }
}
