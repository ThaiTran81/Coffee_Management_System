package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.DTO.ItemDTO;
import com.example.coffee_management_system.DTO.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserDAO {

    public static List<UserDTO> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user JOIN account ON user.email = account.username WHERE account.type > 0";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        List<UserDTO> lst = new ArrayList<UserDTO>();

        while (rs.next()){
            UserDTO user = new UserDTO();
            user.setUserID(rs.getInt("user_id"));
            user.setFullname(rs.getString("fullname"));
            user.setDob(rs.getDate("dob"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setAddress(rs.getString("address"));
            user.setType(rs.getInt("type"));

            lst.add(user);
        }
        return lst;
    }

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

    public static UserDTO findUserByUsername(String username) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE email = '"+username+"'";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()){
            int userID = res.getInt("user_id");
            String fullname = res.getString("fullname");
            String address = res.getString("address");
            String email = res.getString("email");
            String phone = res.getString("phone");
            Date dob = res.getDate("dob");

            UserDTO userDTO = new UserDTO(userID, fullname, email, address, phone, dob);
            return userDTO;
        }
        return null;
    }

    public static int update(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `user`\n" +
                "SET fullname = ?, dob = ?, email = ?, address = ?, phone = ?\n" +
                "WHERE user_id = ?";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        java.sql.Date dob = new java.sql.Date(userDTO.getDob().getTime());

        stmt.setString(1, userDTO.getFullname());
        stmt.setDate(2, dob);
        stmt.setString(3, userDTO.getEmail());
        stmt.setString(4, userDTO.getAddress());
        stmt.setString(5, userDTO.getPhone());
        stmt.setInt(6, userDTO.getUserID());
        return stmt.executeUpdate();
    }
}
