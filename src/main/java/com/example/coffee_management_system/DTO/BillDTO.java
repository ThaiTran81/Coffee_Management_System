package com.example.coffee_management_system.DTO;

import java.util.Date;

public class BillDTO {
    private int bill_id;
    private int user_id;
    private Date create_time = new Date();
    private float discount;
    private int customer_id;
    private String payment_type;
    private int status;
    private int promotion;
    private float total;

    public BillDTO() {
    }

    public BillDTO(int bill_id, int user_id, Date create_time, float discount, int customer_id, String payment_type, int status, int promotion, float total) {
        this.bill_id = bill_id;
        this.user_id = user_id;
        this.create_time = create_time;
        this.discount = discount;
        this.customer_id = customer_id;
        this.payment_type = payment_type;
        this.status = status;
        this.promotion = promotion;
        this.total = total;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "BillDTO{" +
                "bill_id=" + bill_id +
                ", user_id=" + user_id +
                ", create_time=" + create_time +
                ", discount=" + discount +
                ", customer_id=" + customer_id +
                ", payment_type='" + payment_type + '\'' +
                ", status=" + status +
                ", promotion=" + promotion +
                ", total=" + total +
                '}';
    }
}
