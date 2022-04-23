package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.PromotionDTO;
import com.example.coffee_management_system.DTO.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromotionDAO {

    public static List<PromotionDTO> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM promotion WHERE promotion.status > -1";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        List<PromotionDTO> lst = new ArrayList<PromotionDTO>();

        while (rs.next()){
            PromotionDTO promotionDTO = new PromotionDTO();
            promotionDTO.setPromotionID(rs.getInt("promotion_id"));
            promotionDTO.setName(rs.getString("name"));
            promotionDTO.setDescription(rs.getString("description"));
            promotionDTO.setDiscount(rs.getFloat("discount"));
            promotionDTO.setStartDate(rs.getDate("startDate"));
            promotionDTO.setEndDate(rs.getDate("endDate"));
            promotionDTO.setType(rs.getInt("type"));
            promotionDTO.setStatus(rs.getInt("status"));

            lst.add(promotionDTO);
        }
        return lst;
    }

    public static int insert(PromotionDTO promotionDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user(name, descriptor, discount, startDate, endDate, type, status) VALUES(?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        java.sql.Date startDate = new java.sql.Date(promotionDTO.getStartDate().getTime());
        java.sql.Date endDate = new java.sql.Date(promotionDTO.getEndDate().getTime());

        stmt.setString(1,promotionDTO.getName().toString());
        stmt.setString(2, promotionDTO.getDescription().toString());
        stmt.setFloat(3, promotionDTO.getDiscount());
        stmt.setDate(4, startDate);
        stmt.setDate(5, endDate);
        stmt.setInt(6,promotionDTO.getType());
        stmt.setInt(7, promotionDTO.getStatus());

        return stmt.executeUpdate();
    }

    public static PromotionDTO findByPromotionName(String Name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM promotion WHERE name = '"+Name+"'";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()){
            int promotionID = res.getInt("promotion_id");
            String name = res.getString("name");
            String description = res.getString("description");
            float discount = res.getFloat("discount");
            Date startDate = res.getDate("startDate");
            Date endDate = res.getDate("endDate");
            int type = res.getInt("type");
            int status = res.getInt("status");

            PromotionDTO promotionDTO = new PromotionDTO(promotionID, name, description, discount, startDate, endDate, type, status);
            return promotionDTO;
        }
        return null;
    }

    public static int update(PromotionDTO promotionDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `promotion`\n" +
                "SET name = ?, description = ?, discount = ?, startDate = ?, endDate = ?, type = ?, status = ?\n" +
                "WHERE promotion_id = ?";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        java.sql.Date startDate = new java.sql.Date(promotionDTO.getStartDate().getTime());
        java.sql.Date endDate = new java.sql.Date(promotionDTO.getEndDate().getTime());

        stmt.setString(1,promotionDTO.getName().toString());
        stmt.setString(2, promotionDTO.getDescription().toString());
        stmt.setFloat(3, promotionDTO.getDiscount());
        stmt.setDate(4, startDate);
        stmt.setDate(5, endDate);
        stmt.setInt(6,promotionDTO.getType());
        stmt.setInt(7, promotionDTO.getStatus());
        stmt.setInt(8, promotionDTO.getPromotionID());

        return stmt.executeUpdate();
    }

    public static int delete(PromotionDTO promotionDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `promotion`\n" +
                "SET status = -1\n" +
                "WHERE promotion_id = '" + promotionDTO.getPromotionID() + "'";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        return stmt.executeUpdate(sql);
    }

    public static PromotionDTO findByPromotionId(int promotion) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM promotion WHERE promotion_id = "+promotion;
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()){
            int promotionID = res.getInt("promotion_id");
            String name = res.getString("name");
            String description = res.getString("description");
            float discount = res.getFloat("discount");
            Date startDate = res.getDate("startDate");
            Date endDate = res.getDate("endDate");
            int type = res.getInt("type");
            int status = res.getInt("status");

            PromotionDTO promotionDTO = new PromotionDTO(promotionID, name, description, discount, startDate, endDate, type, status);
            return promotionDTO;
        }
        return null;
    }
}
