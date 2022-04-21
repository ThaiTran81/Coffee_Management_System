package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.BillDTO;
import com.example.coffee_management_system.DTO.TableDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public static BillDTO findById(int id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM `bill` where bill_id = ?";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()){
            return new BillDTO(rs.getInt(1),
                    rs.getInt(2),
                    rs.getDate(3),
                    rs.getFloat(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getFloat(9));
        }
        return null;
    }
}
