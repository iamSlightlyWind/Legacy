package fa.training.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaM301DbContext {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=java_ma301;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "Abc123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            JavaM301DbContext.getConnection();
            System.out.println("Database connection established");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
