package com.example.coffee_management_system.DTO;

import java.util.Date;

public class UserDTO {
    int userID;
    String fullname="";
    String email="";
    String address="";
    String phone="";
    int type;
    Date dob=new java.util.Date();

    public UserDTO(int userID, String fullname, String email, String address, String phone, Date dob) {
        this.userID = userID;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
    }

    public UserDTO(String fullname, String email, String address, String phone, Date dob) {
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    public UserDTO(String email, String fullname) {
        this.email = email;
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}