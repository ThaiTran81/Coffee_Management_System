package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.values.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public static List<CategoryDTO> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM category";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        List<CategoryDTO> lst = new ArrayList<CategoryDTO>();

        while (rs.next()){
            CategoryDTO category = new CategoryDTO();
            category.setId(rs.getInt("category_id"));
            category.setName(rs.getString("name"));
            category.setIcUrl(rs.getString("icon_url"));
            lst.add(category);
        }
        return lst;
    }

    public static int insert(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO category (name, icon_url) VALUES(?,?)";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1,categoryDTO.getName());
        stmt.setString(2, categoryDTO.getIcUrl());

        return stmt.executeUpdate();
    }

    public static int delete(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM category WHERE category_id = "+ categoryDTO.getId();
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(sql);
    }

    public static CategoryDTO findByName(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM category WHERE name='"+name+"'";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        CategoryDTO categoryDTO = null;
        if(rs.next()){
            categoryDTO = new CategoryDTO();
            categoryDTO.setName(rs.getString("name"));
            categoryDTO.setId(rs.getInt("category_id"));
            categoryDTO.setIcUrl(rs.getString("icon_url"));
        }

        return categoryDTO;
    }

    public static int update(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE category\n" +
                "SET name = ?\n" +
                "WHERE category_id = ?";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, categoryDTO.getName());
        stmt.setInt(2, categoryDTO.getId());
        return stmt.executeUpdate();
    }
}
