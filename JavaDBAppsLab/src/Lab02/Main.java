package Lab02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = ConnectionManager.getConnection(scanner);

        PreparedStatement statement = connection.prepareStatement("select user_name,first_name,last_name,count(ug.id) from users" +
                " join users_games as ug on users.id = ug.user_id" +
                " where user_name = ?" +
                " group by ug.user_id");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String userName = resultSet.getString("user_name");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Integer gamesCount = Integer.parseInt(resultSet.getString(4));

            System.out.printf("User: %s%n%s %s has played %d games%n", userName, firstName, lastName, gamesCount);
        } else {
            System.out.println("No such user exist");
        }
    }
}
