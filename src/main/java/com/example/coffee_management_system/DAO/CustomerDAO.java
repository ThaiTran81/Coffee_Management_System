package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
