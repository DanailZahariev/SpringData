package addMinion;

import connectorManager.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddMinion {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        Connection connection = ConnectionManager.getConnection(scanner);

        String[] tokens = scanner.nextLine().split("\\s+");

        String minionName = tokens[1];
        int minionAge = Integer.parseInt(tokens[2]);
        String townName = tokens[3];

        tokens = scanner.nextLine().split("\\s+");
        String villainName = tokens[1];

        int townId = getOrInsertTown(connection, townName);
        int villainId = checkIfVillainExistOrAddIt(connection, villainName);

        insertMinions(connection, minionName, minionAge, villainId, townId);

        System.out.printf("Successfully added %s to be minion of %s.", minionName, villainName);
        connection.close();

    }

    private static void insertMinions(Connection connection, String minionName, int minionAge, int villainId, int townId) throws SQLException {
        PreparedStatement insertMinion = connection.prepareStatement("INSERT INTO minions(name, age, town_id) VALUES (?,?,?)");
        insertMinion.setString(1, minionName);
        insertMinion.setInt(2, minionAge);
        insertMinion.setInt(3, townId);

        insertMinion.executeUpdate();

        PreparedStatement getLastMinion = connection.prepareStatement("SELECT id FROM minions ORDER BY id DESC");
        ResultSet lastMinion = getLastMinion.executeQuery();
        lastMinion.next();
        int lastMinionId = lastMinion.getInt("id");


        PreparedStatement insertMinionToVillains = connection.prepareStatement("INSERT INTO minions_villains  VALUES (?,?)");
        insertMinionToVillains.setInt(1, lastMinionId);
        insertMinionToVillains.setInt(2, villainId);
        insertMinionToVillains.executeUpdate();
    }

    private static int getOrInsertTown(Connection connection, String townName) throws SQLException {

        PreparedStatement selectTown = connection.prepareStatement("SELECT id FROM towns WHERE name = ?");
        selectTown.setString(1, townName);

        ResultSet townSet = selectTown.executeQuery();

        int townId = 0;
        if (!townSet.next()) {
            PreparedStatement insertTown = connection.prepareStatement("INSERT INTO towns(name) VALUES (?)");
            insertTown.setString(1, townName);
            insertTown.executeUpdate();

            ResultSet newTownId = selectTown.executeQuery();
            newTownId.next();
            townId = newTownId.getInt("id");

            System.out.printf("Town %s was added to the database.%n", townName);
        } else {
            townId = townSet.getInt("id");
        }
        return townId;
    }

    private static int checkIfVillainExistOrAddIt(Connection connection, String villainName) throws SQLException {

        PreparedStatement selectVillain = connection.prepareStatement("SELECT id FROM villains WHERE name = ?");
        selectVillain.setString(1, villainName);

        ResultSet villainSet = selectVillain.executeQuery();

        int villainId = 0;
        if (!villainSet.next()) {
            PreparedStatement insertVillain = connection.
                    prepareStatement("INSERT INTO villains (name, evilness_factor) VALUES (?, ?)");
            insertVillain.setString(1, villainName);
            insertVillain.setString(2, "evil");
            insertVillain.executeUpdate();

            ResultSet newVillain = selectVillain.executeQuery();
            newVillain.next();
            villainId = newVillain.getInt("id");

            System.out.printf("Villain %s was added to the database.%n", villainName);

        } else {
            villainId = villainSet.getInt("id");
        }
        return villainId;
    }
}
