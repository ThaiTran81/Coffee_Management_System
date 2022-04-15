package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.AreaDTO;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AreaDAO {

    public static ArrayList<AreaDTO> findAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM area";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<AreaDTO> listArea = new ArrayList<AreaDTO>();
        if (rs.next()){
            listArea.add(new AreaDTO(rs.getInt(0),rs.getString(1),rs.getInt(2)));
        }

        return listArea;
    }

    public static ArrayList<String> findAllAreaName() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM area";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<String> listAreaName = new ArrayList<String>();
        if (rs.next()){
            listAreaName.add(rs.getString(2));
            System.out.println("hehehehehe");
        }

        return listAreaName;
    }

    public static AreaDTO findById(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM area where id = ?";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()){
            return new AreaDTO(rs.getInt(1),rs.getString(2),rs.getInt(3));
        }else{
            return null;
        }
    }
}
