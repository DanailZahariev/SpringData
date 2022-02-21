package getMinionsNames;

import connectorManager.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        Connection connection = ConnectionManager.getConnection(scanner);

        System.out.println("Enter Villain id: ");

        int villainId = Integer.parseInt(scanner.nextLine());

        getVillainMinionsNames(connection, villainId);
        connection.close();
    }

    private static void getVillainMinionsNames(Connection connection, int villainId) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT m.name, m.age\n" +
                "FROM minions AS m JOIN minions_villains AS mv on m.id = mv.minion_id\n" +
                "WHERE mv.villain_id = ?");

        statement.setInt(1, villainId);

        ResultSet resultSet = statement.executeQuery();

        int num = 1;
        if (!resultSet.next()) {
            System.out.printf("No villain with ID %d exists in the database.", villainId);
        } else {
            while (resultSet.next()) {
                System.out.println(num + ". " + resultSet.getString("m.name") + " " + resultSet.getInt("m.age"));
                num++;
            }
        }
    }
}
