package com.example.coffee_management_system.DAO;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;

public class AccountDAO {

    static public boolean authenticate(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT username, password FROM ACCOUNT WHERE username = 'admin'";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        String stored_hash="";

        if (rs.next()){
            stored_hash = rs.getString("password");
        }
        if (BCrypt.checkpw(password, stored_hash))
            System.out.println("It matches");
        else
            System.out.println("It does not match");
        return false;
    }

    public static boolean checkAdminExist() throws SQLException, ClassNotFoundException {
        String sql = "SELECT username FROM ACCOUNT WHERE type = 0";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        return rs.next();
    }

    public static int insert(String username, String password, int type) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO account (username, password, type) VALUES(?,?,?)";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1,username);
        stmt.setString(2,password);
        stmt.setInt(3,type);

        return stmt.executeUpdate();
    }
}
