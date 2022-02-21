package removeVillain;

import connectorManager.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        Connection connection = ConnectionManager.getConnection(scanner);

        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement selectVillain = connection.prepareStatement("SELECT name FROM villains WHERE id = ?");
        selectVillain.setInt(1, villainId);

        ResultSet villainData = selectVillain.executeQuery();

        if (!villainData.next()) {
            System.out.println("No such villain was found");
            return;
        }

        String villainName = villainData.getString("name");

        PreparedStatement selectAllVillainMinions = connection.prepareStatement("SELECT COUNT(minion_id) AS count FROM minions_villains\n" +
                "WHERE villain_id = ?");
        selectAllVillainMinions.setInt(1, villainId);
        ResultSet minionsCount = selectAllVillainMinions.executeQuery();
        minionsCount.next();
        int countMinionsDeleted = minionsCount.getInt("count");

        connection.setAutoCommit(false);

        try {
            deleteVillainAndReleaseMinions(connection, villainId, villainName, countMinionsDeleted);
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    private static void deleteVillainAndReleaseMinions(Connection connection, int villainId, String villainName, int countMinionsDeleted) throws SQLException {
        PreparedStatement deleteMinionsFromVillains = connection.prepareStatement("DELETE FROM minions_villains WHERE villain_id = ?");
        deleteMinionsFromVillains.setInt(1, villainId);
        deleteMinionsFromVillains.executeUpdate();

        PreparedStatement deleteVillain = connection.prepareStatement("DELETE FROM villains WHERE id = ?");
        deleteVillain.setInt(1, villainId);
        deleteVillain.executeUpdate();

        connection.commit();
        System.out.printf("%s was deleted%n", villainName);
        System.out.printf("%d minions released", countMinionsDeleted);
    }
}
