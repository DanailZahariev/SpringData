package increaseMinionAge;

import connectorManager.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        Connection connection = ConnectionManager.getConnection(scanner);

        System.out.println("Enter Minions ID: ");
        List<Integer> minionsId = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        ;

        PreparedStatement updateMinionsAge = connection.
                prepareStatement("UPDATE minions SET age = age + 1, name = LOWER(name) WHERE id = ?");
        for (Integer id : minionsId) {
            updateMinionsAge.setInt(1, id);

            updateMinionsAge.executeUpdate();
        }
        PreparedStatement printMinions = connection.prepareStatement("SELECT name,age FROM minions");
        ResultSet allMinions = printMinions.executeQuery();
        while (allMinions.next()) {
            System.out.println(allMinions.getString("name") + " " + allMinions.getInt("age"));
        }
        connection.close();
    }
}
