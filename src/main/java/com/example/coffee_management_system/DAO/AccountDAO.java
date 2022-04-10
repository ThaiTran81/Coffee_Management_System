package com.example.coffee_management_system.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {

    boolean authenticate(String username, String password){

        return false;
    }

    public static boolean checkAdminExist() throws SQLException, ClassNotFoundException {
        String sql = "SELECT username FROM ACCOUNT WHERE username = 'admin'";
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        return rs.next();
    }
}
