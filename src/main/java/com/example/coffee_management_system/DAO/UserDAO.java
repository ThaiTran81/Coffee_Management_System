package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class UserDAO {

    public static int insert(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user(fullname, dob, email, address, phone) VALUES(?,?,?,?,?)";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        java.sql.Date dob = new java.sql.Date(userDTO.getDob().getTime());

        stmt.setString(1,userDTO.getFullname().toString());
        stmt.setDate(2, dob);
        stmt.setString(3,userDTO.getEmail().toString());
        stmt.setString(4, userDTO.getAddress().toString());
        stmt.setString(5,userDTO.getPhone().toString());

        return stmt.executeUpdate();
    }
}
