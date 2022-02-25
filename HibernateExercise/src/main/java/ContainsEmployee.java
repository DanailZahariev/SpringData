import entities.EntityFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {

        EntityManagerFactory factory = EntityFactory.getEntityManager().getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");

        String name = scanner.nextLine();


        Long count = entityManager.createQuery("SELECT COUNT(e.id) FROM Employee e WHERE " +
                        "CONCAT(e.firstName, ' ', e.lastName) = :full_name", Long.class).
                setParameter("full_name", name).getSingleResult();

        if (count == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }
}
