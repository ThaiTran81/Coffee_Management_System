package com.example.coffee_management_system.DAO;


import java.sql.*;

public class DBConnection {
    private static DBConnection dbConnection = null;
    private Connection conn;

    String url ="jdbc:mysql://localhost:8889/cms_db";
    String user = "root";
    String password = "root";

    public DBConnection() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url,user,password);
    }

    public Connection getConnection(){
        return conn;
    }

    public static DBConnection getDbConnection() throws SQLException, ClassNotFoundException {
        if (dbConnection == null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public void getAllUser() throws SQLException {
        String sql = "SELECT * FROM USER";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if(rs.next()) {
            int id = rs.getInt("user_id");
            String str1 = rs.getString("email");
            System.out.println(id+"-"+str1);
        }
    }
}
