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

    public static int updateByEmail(String oldEmail, String newEmail) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `account`\n" +
                "SET username = ?\n" +
                "WHERE username = ?";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, newEmail);
        stmt.setString(2, oldEmail);
        return stmt.executeUpdate();
    }

    public static int updatePassword(String email, String password, int type) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `account`\n" +
                "SET password = ?\n" +
                "WHERE username = ? and type = ?";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed_password);
        stmt.setString(1, hashed_password);
        stmt.setString(2, email);
        stmt.setInt(3,type);
        return stmt.executeUpdate();
    }
}
