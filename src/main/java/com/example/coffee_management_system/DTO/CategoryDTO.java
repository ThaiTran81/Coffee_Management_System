package com.example.coffee_management_system.DTO;

public class CategoryDTO {
    String name;
    int id;
    String icUrl;

    public CategoryDTO(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public CategoryDTO(String name, int id, String icUrl) {
        this.name = name;
        this.id = id;
        this.icUrl = icUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcUrl() {
        return icUrl;
    }

    public void setIcUrl(String icUrl) {
        this.icUrl = icUrl;
    }
}
