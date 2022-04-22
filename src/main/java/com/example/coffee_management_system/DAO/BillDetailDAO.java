package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.AreaDTO;
import com.example.coffee_management_system.DTO.BillDetailDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
