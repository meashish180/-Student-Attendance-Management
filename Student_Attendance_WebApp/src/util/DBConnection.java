package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/attendance_db";
    private static final String USER = "root";         // your MySQL username
    private static final String PASSWORD = "nethraa";         // your MySQL password if any

    public static Connection getConnection() throws SQLException {
        try {
            // load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
        // create and return the connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}