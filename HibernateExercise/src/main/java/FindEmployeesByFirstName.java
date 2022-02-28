import entities.Employee;
import entities.EntityFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Scanner;

public class FindEmployeesByFirstName {
    public static void main(String[] args) {

        EntityManagerFactory factory = EntityFactory.getEntityManager().getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name letters: ");
        String name = scanner.nextLine();

        entityManager.createQuery("SELECT e FROM Employee e WHERE e.firstName LIKE :name", Employee.class).setParameter("name",name + '%')
                .getResultList().forEach(employee -> {
                    System.out.printf("%s %s - %s ($%.2f)%n",employee.getFirstName(),employee.getLastName(),employee.getJobTitle(),employee.getSalary());
                });
    }
}
