package connectorManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class ConnectionManager {
    public static Connection getConnection(Scanner scanner) throws SQLException {

        System.out.print("Enter username default (root): ");
        String user = scanner.nextLine();

        /*Default username is root press Enter to skip*/

        user = user.equals("") ? "root" : user;
        System.out.println();

        /*Enter you password in order to access you database*/

        System.out.print("Enter password default (empty): ");
        String password = scanner.nextLine().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);
    }
}
