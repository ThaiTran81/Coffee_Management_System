package com.example.coffee_management_system.DTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UserDTO {
    int userID;
    String fullname="";
    String email="";
    String address="";
    String phone="";
    int type;
    Date dob=new java.util.Date();

    public UserDTO() {
        this.userID = -1;
        this.fullname = "";
        this.email = "";
        this.address = "";
        this.phone = "";
        this.dob = null;
    }

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

    public LocalDate getDobAsLocalDate() {
        return Instant.ofEpochMilli(dob.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
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

    public void setDob(LocalDate value) { this.dob = Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()); }

    public String createPassword() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(this.dob).replace("-", "");
    }
}
