package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnector {

    private static Connection connection;
    private final static String connectionString = "jdbc:mysql://localhost:3306/";

    private MyConnector() {
    }


    public static void createConnection(String username, String password, String dbName) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);

        connection = DriverManager.getConnection(connectionString + dbName, properties);
    }

    public static Connection getConnection() {
        return connection;
    }
}
