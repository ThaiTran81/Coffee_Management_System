package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.TableDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDAO {
    public static List<TableDTO> findAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `table`";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        List<TableDTO> listArea = new ArrayList<TableDTO>();
        while (rs.next()){
            listArea.add(new TableDTO(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5)
            ));
        }

        return listArea;
    }

    public static List<TableDTO> findAvailableTableByAreaId(int id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM `table` where area_id = ? and status = 1";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet rs = preparedStatement.executeQuery();

        List<TableDTO> listAreaName = new ArrayList<TableDTO>();
        while (rs.next()){
            listAreaName.add(new TableDTO(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5))
            );
        }

        return listAreaName;
    }

    public static TableDTO findByName(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `table` WHERE name='"+name+"'";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        TableDTO tableDTO = null;
        if(rs.next()){
            tableDTO = new TableDTO(rs.getInt("table_id"),
                    rs.getInt("area_id"),
                    rs.getString("name"),
                    rs.getInt("bill_id"),
                    rs.getInt("status"));
        }

        return tableDTO;
    }

    public static int insert(TableDTO tableDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `table`(area_id, name, bill_id, status) VALUES(?,?,?,?)";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1,tableDTO.getArea_id());
        stmt.setString(2,tableDTO.getName());
        stmt.setInt(3, tableDTO.getBill_id());
        stmt.setInt(4, tableDTO.getStatus());

        return stmt.executeUpdate();
    }

    public static int update(TableDTO tableDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `table`\n" +
                "SET bill_id = ? and " +
                "status = ? " +
                "WHERE table_id = ?";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,tableDTO.getBill_id());
        stmt.setInt(2,tableDTO.getStatus());
        stmt.setInt(3, tableDTO.getTable_id());
        return stmt.executeUpdate();
    }

    public static int setBillId(int tableId, int newBillId) throws SQLException, ClassNotFoundException {
        String sql;
        if(newBillId == 0){
            sql = "UPDATE `table`\n" +
                    "SET bill_id = " + null +
                    " WHERE table_id = " + tableId;
        }else{
            sql = "UPDATE `table`\n" +
                    "SET bill_id = " + newBillId +
                    " WHERE table_id = " + tableId;
        }

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        return stmt.executeUpdate();
    }

    public static int delete(TableDTO tableDTO) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM `table` WHERE table_id = "+ tableDTO.getTable_id();
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(sql);
    }

    public static List<TableDTO> findAllAvailableTable() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `table` WHERE status=1";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        List<TableDTO> listArea = new ArrayList<TableDTO>();
        while (rs.next()){
            listArea.add(new TableDTO(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5)
            ));
        }

        return listArea;
    }
}
