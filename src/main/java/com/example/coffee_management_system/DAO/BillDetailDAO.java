package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.AreaDTO;
import com.example.coffee_management_system.DTO.BillDetailDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDetailDAO {
    public static List<BillDetailDTO> findAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM billdetail";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        List<BillDetailDTO> listBillDetail = new ArrayList<BillDetailDTO>();
        while (rs.next()){
            listBillDetail.add(new BillDetailDTO(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getFloat(6),
                    rs.getFloat(7)
            ));
        }

        return listBillDetail;
    }

    public static List<BillDetailDTO> findByBillID(int idBill) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM billdetail WHERE bill_id = '" + idBill + "'";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        List<BillDetailDTO> listBillDetail = new ArrayList<BillDetailDTO>();
        while (rs.next()){
            listBillDetail.add(new BillDetailDTO(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getFloat(6),
                    rs.getFloat(7)
            ));
        }

        return listBillDetail;
    }

    public static int insert(BillDetailDTO billDetailDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO billdetail(bill_id, item_id, quantity, note, discount, price) VALUES(?,?,?,?,?,?)";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,billDetailDTO.getBill_id());
        preparedStatement.setInt(2,billDetailDTO.getItem_id());
        preparedStatement.setInt(3,billDetailDTO.getQuantity());
        preparedStatement.setString(4,billDetailDTO.getNote());
        preparedStatement.setFloat(5,billDetailDTO.getDiscount());
        preparedStatement.setFloat(6,billDetailDTO.getPrice());

        preparedStatement.executeUpdate();
        int key = 0;
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()){
            key = rs.getInt(1);
        }
        return key;
    }

    public static void delete(BillDetailDTO billDetailDTO) {
        String sql = "DELETE FROM billdetail WHERE billDetail_id = " + billDetailDTO.getId();
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void update(BillDetailDTO billDetailDTO) {
        String sql = "UPDATE billdetail\n" +
                "SET quantity = ?, price = ? \n" +
                "WHERE billDetail_id = ? ;";
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, billDetailDTO.getQuantity());
            preparedStatement.setFloat(2,billDetailDTO.getPrice());
            preparedStatement.setInt(3, billDetailDTO.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
