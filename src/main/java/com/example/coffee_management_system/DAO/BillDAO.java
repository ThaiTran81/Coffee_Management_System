package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.AreaDTO;
import com.example.coffee_management_system.DTO.BillDTO;
import com.example.coffee_management_system.DTO.ItemUsage;
import com.example.coffee_management_system.DTO.Profit;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public  static List<Profit> getProfitByRangeDate(LocalDate startDate, LocalDate endDate) throws SQLException, ClassNotFoundException {
        String sql = "SELECT\n" +
                "\tbill_id,\n" +
                "\tcreate_time,\n" +
                "\ttotal \n" +
                "FROM\n" +
                "\tbill \n" +
                "WHERE\n" +
                "\t`status` = 1 \n" +
                "\tAND create_time > ? \n" +
                "\tAND create_time < ? \n" +
                "ORDER BY\n" +
                "\tcreate_time ASC";

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String startDateString = startDate.format(formatter);
        String endDateString = endDate.format(formatter);


        stmt.setString(1, startDateString);
        stmt.setString(2,endDateString);

        ResultSet rs = stmt.executeQuery();

        List<Profit> lst = new ArrayList<>();

        while (rs.next()){
            Profit profit = new Profit();

            profit.setBill_id(rs.getInt("bill_id"));
            profit.setCreate_time(rs.getTimestamp("create_time"));
            profit.setTotal(rs.getDouble("total"));

            lst.add(profit);
        }

        return lst;
    }    
    public  static List<ItemUsage> getItemUsageByRangeDate(LocalDate startDate, LocalDate endDate) throws SQLException, ClassNotFoundException {
        String sql = "SELECT\n" +
                "i.item_id,\n" +
                "i.`name`,\n" +
                "COUNT(*) AS `usage` ,\n" +
                "SUM(bd.price)\n" +
                "FROM\n" +
                "( bill b JOIN billdetail bd ON b.bill_id = bd.bill_id )\n" +
                "JOIN item i ON bd.item_id = i.item_id \n" +
                "WHERE\n" +
                "b.`status` = 1 \n" +
                "AND create_time > ? \n" +
                "AND create_time < ? \n" +
                "GROUP BY\n" +
                "i.item_id,\n" +
                "i.`name` \n" +
                "ORDER BY COUNT(*) ASC";

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String startDateString = startDate.format(formatter);
        String endDateString = endDate.format(formatter);


        stmt.setString(1, startDateString);
        stmt.setString(2,endDateString);

        ResultSet rs = stmt.executeQuery();

        List<ItemUsage> lst = new ArrayList<>();

        while (rs.next()){
            ItemUsage itemUsage = new ItemUsage();

            itemUsage.setItem_id(rs.getInt("item_id"));
            itemUsage.setName(rs.getString("name"));
            itemUsage.setUsage(rs.getInt("usage"));

            System.out.println(itemUsage.getUsage());

            lst.add(itemUsage);
        }

        return lst;
    }


    public static BillDTO findById(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM bill where bill_id = ?";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()){
            return new BillDTO(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getDate(3),
                    rs.getFloat(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getFloat(9));
        }else{
            return null;
        }
    }

    public static int countBill() {
        String sql = "SELECT count(*) FROM bill";
        Connection connection = null;
        int size = 0;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){

                size = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return size;
    }

    public static int insert(BillDTO billDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `bill`(user_id, create_time, status) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement stmt = null;

        connection = DBConnection.getDbConnection().getConnection();
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, billDTO.getUser_id());
        stmt.setDate(2,  new java.sql.Date(billDTO.getCreate_time().getTime()));
        stmt.setInt(3, billDTO.getStatus());
        return stmt.executeUpdate();

    }

    public static int getStatusBill(int bill_id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT status FROM bill WHERE bill_id = " + bill_id;
        Connection connection = null;
        int status = 1;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                status = rs.getInt("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return status;
    }
}
