import entities.Address;
import entities.EntityFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Scanner;

public class AddingNewAddress {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = EntityFactory.getEntityManager().getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        String addressName = "Vitoshka 15";

        Address address = new Address();
        address.setText(addressName);
        entityManager.persist(address);

        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine();

        entityManager.createQuery("UPDATE Employee e SET e.address = :address WHERE e.lastName = :name")
                .setParameter("name", lastName).setParameter("address", address)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
