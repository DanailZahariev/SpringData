import entities.EntityFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class EmployeesWithSalaryOver {
    public static void main(String[] args) {

        EntityManagerFactory factory = EntityFactory.getEntityManager().getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        List<String> firstName = entityManager.
                createQuery("SELECT e.firstName FROM Employee AS e WHERE e.salary > 50000", String.class).getResultList();
        firstName.forEach(System.out::println);
    }
}
