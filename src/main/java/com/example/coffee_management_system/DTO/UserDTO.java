package com.example.coffee_management_system.DTO;

import java.util.Date;

public class UserDTO {
    String fullname="";
    String email="";
    String address="";
    String phone="";
    Date dob=new java.util.Date();

    public UserDTO(String fullname, String email, String address, String phone, Date dob) {
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
    }

    public UserDTO(String email) {
        this.email = email;
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
}
