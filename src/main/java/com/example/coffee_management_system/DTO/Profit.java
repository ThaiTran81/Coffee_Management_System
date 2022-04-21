package com.example.coffee_management_system.DTO;

import java.sql.Timestamp;

public class Profit {
    int bill_id;
    Timestamp create_time;
    double total;

    public Profit(){

    }
    public Profit(int bill_id, Timestamp create_time, double total) {
        this.bill_id = bill_id;
        this.create_time = create_time;
        this.total = total;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
