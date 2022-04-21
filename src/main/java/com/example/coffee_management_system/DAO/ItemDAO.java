package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.ItemDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public static List<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item WHERE `status` > 0 OR `status` IS NULL";
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

    public static List<ItemDTO> getByCategory(int catID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item WHERE category="+catID+" AND (`status` > 0 OR `status` IS NULL)";
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

    public static List<ItemDTO> getByCategoryAndName(int catID, String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item WHERE category="+catID+" AND name like'%"+name+"%'"+" AND (`status` > 0 OR `status` IS NULL)";
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

    public static int delete(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `item`\n" +
                "SET `status` = 0\n" +
                "WHERE `item_id` = "+itemDTO.getItem_id();
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();


        return stmt.executeUpdate(sql);
    }

    public static int update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `item` " +
                "SET `name`= ?," +
                " `category` = ?," +
                " `description` = ?," +
                " `price` = ? "+
                "WHERE `item_id` = ?";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, itemDTO.getName());
        stmt.setInt(2, itemDTO.getCategory());
        stmt.setString(3, itemDTO.getDescription());
        stmt.setDouble(4, itemDTO.getPrice());
        stmt.setInt(5, itemDTO.getItem_id());

        return stmt.executeUpdate();
    }

    public static int insert(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item (name, category, description, price) VALUES(?,?,?,?)";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1,itemDTO.getName());
        stmt.setInt(2, itemDTO.getCategory());
        stmt.setString(3, itemDTO.getDescription());
        stmt.setDouble(4, itemDTO.getPrice());

        return stmt.executeUpdate();
    }

    public static ItemDTO findById(int item_id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item WHERE item_id='"+item_id+"'";
        Connection connection = DBConnection.getDbConnection().getConnection();

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        ItemDTO itemDTO = null;

        if (rs.next()){
            itemDTO = new ItemDTO();

            itemDTO.setItem_id(rs.getInt("item_id"));
            itemDTO.setName(rs.getString("name"));
            itemDTO.setDescription(rs.getString("description"));
            itemDTO.setCategory(rs.getInt("category"));
            itemDTO.setPrice(rs.getDouble("price"));

        }

        return itemDTO;
    }

}
