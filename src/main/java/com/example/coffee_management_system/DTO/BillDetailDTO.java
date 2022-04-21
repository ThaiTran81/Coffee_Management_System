package com.example.coffee_management_system.DTO;

public class BillDetailDTO {
    private int id;
    private int bill_id;
    private int item_id;
    private int quantity;
    private String note;
    private float discount;
    private float price;

    public BillDetailDTO() {
    }

    public BillDetailDTO(int id, int bill_id, int item_id, int quantity, String note, float discount, float price) {
        this.id = id;
        this.bill_id = bill_id;
        this.item_id = item_id;
        this.quantity = quantity;
        this.note = note;
        this.discount = discount;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
