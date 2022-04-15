package com.example.coffee_management_system.DTO;

public class TableDTO {
    private int table_id;
    private int area_id;
    private int name;
    private int bill_id;
    private int status;

    public TableDTO() {
    }

    public TableDTO(int table_id, int area_id, int name, int bill_id, int status) {
        this.table_id = table_id;
        this.area_id = area_id;
        this.name = name;
        this.bill_id = bill_id;
        this.status = status;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
