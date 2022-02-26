import entities.Employee;
import entities.EntityFactory;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Comparator;
import java.util.Scanner;

public class GetEmployeeWIthProject {
    public static void main(String[] args) {

        EntityManagerFactory factory = EntityFactory.getEntityManager().getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter employee id: ");
        int employeeId = Integer.parseInt(scanner.nextLine());

        entityManager.createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class)
                .setParameter("id", employeeId).getResultList().forEach(employee -> {
                    System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getDepartment().getName());

                    employee.getProjects().stream().sorted(Comparator.comparing(Project::getName))
                            .forEach(project -> System.out.printf("%s%n", project.getName()));
                });
    }
}
