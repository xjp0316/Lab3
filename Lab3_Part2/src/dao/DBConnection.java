package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    private static Connection instance;

    private DBConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.postgresql.Driver");
                instance = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected via DBConnection.");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
