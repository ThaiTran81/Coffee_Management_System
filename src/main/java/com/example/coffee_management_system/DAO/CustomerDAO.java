package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.CustomerDTO;

import java.sql.*;

public class CustomerDAO {

    public static CustomerDTO findById(int id) throws SQLException, ClassNotFoundException {
        String sql = "Select * from customer where customer_id = " + id;
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            return new CustomerDTO(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getDate(5)
            );
        }
        return null;
    }

    public static CustomerDTO findByPhone(String phone) throws SQLException, ClassNotFoundException {
        String sql = "Select * from customer where phone = " + phone;
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            return new CustomerDTO(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getDate(5)
            );
        }
        return null;
    }

    public static int insert(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `customer`(fullname, phone) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement stmt = null;
        connection = DBConnection.getDbConnection().getConnection();
        stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, customerDTO.getFullname());
        stmt.setString(2, customerDTO.getPhone());
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        int key = 0;
        if(rs.next()){
            key = rs.getInt(1);
        }
        return key;
    }
}
