package com.example.administrator.bottom.net;

/**
 * Created by Administrator on 2017/11/26 0026.
 */

public class Order {
    private String orderNum;
    private String phone;
    private String time;
    private String location;
    private String note;
    private String status;
    private String date;

    public Order(String orderNum, String phone, String time, String location, String note, String status,String date) {
        this.orderNum = orderNum;
        this.phone = phone;
        this.time = time;
        this.location = location;
        this.note = note;
        this.status = status;
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public String getNote() {
        return note;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

}
