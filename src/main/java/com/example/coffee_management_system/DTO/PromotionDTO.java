package com.example.coffee_management_system.DTO;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PromotionDTO {
    int promotionID;
    String name="";
    String description="";
    float discount;
    Date startDate = new Date();
    Date endDate = new Date();
    int type;
    int status;

    public PromotionDTO() {
        this.promotionID = -1;
        this.name = "";
        this.description = "";
        this.discount = -1;
        this.startDate = null;
        this.endDate = null;
        this.type = -1;
        this.status = -1;
    }

    public PromotionDTO(int promotionID, String name, String description, float discount, Date startDate, Date endDate, int type, int status) {
        this.promotionID = promotionID;
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
    }

    public PromotionDTO(String name, String description, float discount, Date startDate, Date endDate, int type, int status) {
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
    }

    public PromotionDTO(int promotionID, String name, String description, float discount, Date startDate, Date endDate, int status) {
        this.promotionID = promotionID;
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public PromotionDTO(String name, String description, float discount, Date startDate, Date endDate, int status) {
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public LocalDate getstartDateAsLocalDate() {
        return Instant.ofEpochMilli(startDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalDate getendDateAsLocalDate() {
        return Instant.ofEpochMilli(endDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public void setStartDate(LocalDate value) { this.startDate = Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()); }

    public void setEndDate(LocalDate value) { this.endDate = Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()); }


    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
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

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
