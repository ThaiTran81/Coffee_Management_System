package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.ItemDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public static List<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item";
        Connection connection = DBConnection.getDbConnection().getConnection();

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        List<ItemDTO> lst = new ArrayList<>();

        while (rs.next()){
            ItemDTO itemDTO = new ItemDTO();

            itemDTO.setItem_id(rs.getInt("item_id"));
            itemDTO.setName(rs.getString("name"));
            itemDTO.setDescription(rs.getString("description"));
            itemDTO.setCategory(rs.getInt("category"));
            itemDTO.setPrice(rs.getDouble("price"));

            lst.add(itemDTO);
        }

        return lst;

    }
}
