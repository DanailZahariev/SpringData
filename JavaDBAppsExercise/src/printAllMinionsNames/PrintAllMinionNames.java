package printAllMinionsNames;

import connectorManager.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        Connection connection = ConnectionManager.getConnection(scanner);
        List<String> minions = new ArrayList<>();

        PreparedStatement selectAllMinionNames = connection.prepareStatement("SELECT name FROM minions");
        ResultSet namesSet = selectAllMinionNames.executeQuery();

        while (namesSet.next()) {
            String minionsNames = namesSet.getString(1);
            minions.add(minionsNames);
        }
        int startIndex = 0;
        int endIndex = minions.size() - 1;

        for (int i = 0; i < minions.size(); i++) {
            String output = i % 2 == 0 ? minions.get(startIndex++) : minions.get(endIndex--);
            System.out.println(output);
        }
        connection.close();
    }
}
