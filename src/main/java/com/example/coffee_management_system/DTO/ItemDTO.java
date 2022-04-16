package com.example.coffee_management_system.DTO;

public class ItemDTO {
    int item_id;
    String name;
    String description;
    int category;
    double price;

    public ItemDTO() {
    }

    public ItemDTO(int item_id, String name, String description, int category, double price) {
        this.item_id = item_id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
