package getVilliansName;

import connectorManager.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetVillainName {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        Connection connection = ConnectionManager.getConnection(scanner);

        PreparedStatement statement = connection.prepareStatement("SELECT name,  COUNT(DISTINCT mv.minion_id) AS villain_min " +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS  mv ON v.id = mv.villain_id\n" +
                "GROUP BY v.name\n" +
                "HAVING villain_min > 15\n" +
                "ORDER BY  villain_min DESC");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            System.out.printf("%s %d", resultSet.getString("name"), resultSet.getInt("villain_min"));
        }
        connection.close();
    }
}
