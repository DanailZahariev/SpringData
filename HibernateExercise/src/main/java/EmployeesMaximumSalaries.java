import entities.EntityFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {

        EntityManagerFactory factory = EntityFactory.getEntityManager().getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        @SuppressWarnings("unchecked")
        List<Object[]> resultList = entityManager.createNativeQuery("SELECT d.name,MAX(e.salary) AS max_salary\n" +
                "FROM employees AS e\n" +
                "         JOIN departments AS d ON d.department_id = e.department_id\n" +
                "GROUP BY d.name\n" +
                "HAVING max_salary NOT BETWEEN 30000 AND 70000\n").getResultList();
        resultList.forEach(result -> {
            System.out.printf("%s %.2f%n",result[0],result[1]);
        });
    }
}
