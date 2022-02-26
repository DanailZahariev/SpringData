import entities.Employee;
import entities.EntityFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Set;

public class IncreaseSalaries {
    public static void main(String[] args) {

        EntityManagerFactory factory = EntityFactory.getEntityManager().getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        Set<String> depNames = Set.of("Engineering", "Tool Design", "Marketing", "Information Services");

        entityManager.getTransaction().begin();

        entityManager.createQuery("UPDATE Employee e SET e.salary = e.salary * 1.12 " +
                "WHERE e.department.id IN :ids").setParameter("ids", Set.of(1, 2, 4, 11)).executeUpdate();

        entityManager.createQuery("SELECT e FROM Employee e WHERE e.department.name IN (:department_names)", Employee.class)
                .setParameter("department_names", depNames).getResultList().forEach(employee ->
                        System.out.printf("%s %s ($%.2f)%n", employee.getFirstName(), employee.getLastName(), employee.getSalary()));

        entityManager.getTransaction().commit();


    }
}
