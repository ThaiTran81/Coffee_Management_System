package com.example.coffee_management_system.DAO;

import com.example.coffee_management_system.values.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;

public class AccountDAO {

    static public boolean authenticate(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT username, password, type FROM ACCOUNT WHERE username = '"+username+"'";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        String stored_hash="";

        if (rs.next()){
            stored_hash = rs.getString("password");
            User.role = rs.getInt("type");
            return BCrypt.checkpw(password, stored_hash);
        }
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
