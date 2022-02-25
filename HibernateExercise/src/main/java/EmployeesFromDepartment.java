import entities.Employee;
import entities.EntityFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EmployeesFromDepartment {
    public static void main(String[] args) {

        EntityManagerFactory factory = EntityFactory.getEntityManager().getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.department.name = :depName ORDER BY e.salary,e.id", Employee.class)
                .setParameter("depName","Research and Development")
                .getResultList().forEach(employee -> System.out.printf("%s %s from %s - $%.2f%n",employee.getFirstName(),employee.getLastName(),
                        employee.getDepartment().getName(),employee.getSalary()));
    }
}
