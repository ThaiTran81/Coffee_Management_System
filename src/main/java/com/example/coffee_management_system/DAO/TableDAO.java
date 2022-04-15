package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.AreaDTO;
import com.example.coffee_management_system.DTO.TableDTO;

import java.sql.*;
import java.util.ArrayList;

public class TableDAO {
    public static ArrayList<TableDTO> findAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM table";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<TableDTO> listArea = new ArrayList<TableDTO>();
        if (rs.next()){
            listArea.add(new TableDTO(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5)
            ));
        }

        return listArea;
    }

    public static ArrayList<TableDTO> findByAreaId(int id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM table where table_id = ?";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet rs = preparedStatement.executeQuery();

        ArrayList<TableDTO> listAreaName = new ArrayList<TableDTO>();
        if (rs.next()){
            listAreaName.add(new TableDTO(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5))
            );
        }

        return listAreaName;
    }
}
