package com.example.coffee_management_system.DTO;

import java.util.Date;

public class CustomerDTO {
    private int customer_id;
    private String fullname;
    private String phone;
    private int point;
    private Date date;

    public CustomerDTO() {
    }

    public CustomerDTO(int customer_id, String fullname, String phone, int point, Date date) {
        this.customer_id = customer_id;
        this.fullname = fullname;
        this.phone = phone;
        this.point = point;
        this.date = date;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
