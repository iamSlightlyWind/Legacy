package fa.training.utils;

import java.sql.*;
import java.util.logging.*;

public class Database {

    public static Connection connection;

    static {
        try {
            String username = "sa";
            String password = "123";
            String database = "java_ma301";

            String url = "jdbc:sqlserver://localhost:1433;databaseName=" + database;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}