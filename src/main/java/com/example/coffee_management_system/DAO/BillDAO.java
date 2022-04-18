package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.DTO.ItemUsage;
import com.example.coffee_management_system.DTO.Profit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    }    public  static List<ItemUsage> getItemUsageByRangeDate(LocalDate startDate, LocalDate endDate) throws SQLException, ClassNotFoundException {
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


}
