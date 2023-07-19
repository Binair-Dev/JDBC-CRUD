package be.bnair.utils;

import be.bnair.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:mysql://IP:PORT/DB_NAME?serverTimezone=UTC";
    private static final String USERNAME = "DB_USER";
    private static final String PASSWORD = "DB_PASS";
    private static Connection connection = null;
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            if(Constants.DEBUG)
                System.out.println("Connection réussie");
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            if(Constants.DEBUG)
                System.out.println("Connection fermée");
            connection.close();
            connection = null;
        }
    }

}