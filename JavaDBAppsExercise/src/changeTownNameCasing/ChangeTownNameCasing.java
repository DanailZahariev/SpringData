package changeTownNameCasing;

import connectorManager.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNameCasing {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        Connection connection = ConnectionManager.getConnection(scanner);

        String countryName = scanner.nextLine();

        PreparedStatement updateTownNames = connection.prepareStatement("UPDATE towns SET name = UPPER(name) WHERE country = ?");
        updateTownNames.setString(1, countryName);

        int countUpdated = updateTownNames.executeUpdate();

        if (countUpdated == 0) {
            System.out.println("No town names were affected.");
            return;
        }

        System.out.printf("%d town names were affected.%n", countUpdated);

        PreparedStatement selectAllTowns = connection.prepareStatement("SELECT name FROM towns WHERE country = ?");
        selectAllTowns.setString(1, countryName);

        ResultSet allTowns = selectAllTowns.executeQuery();

        List<String> townsNames = new ArrayList<>();

        while (allTowns.next()) {
            String townName = allTowns.getString("name");
            townsNames.add(townName);
        }
        System.out.println(townsNames);

        connection.close();
    }
}
