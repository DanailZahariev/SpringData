
import orm.EntityManager;

import java.sql.Connection;
import java.sql.SQLException;

import static orm.MyConnector.createConnection;
import static orm.MyConnector.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException {

        createConnection("root", "Dani3344", "custom-orm");

        Connection connection = getConnection();

        EntityManager<Object> userEntityManager = new EntityManager<>(connection);
    }
}