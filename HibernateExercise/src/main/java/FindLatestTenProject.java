import entities.EntityFactory;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.format.DateTimeFormatter;

public class FindLatestTenProject {
    public static void main(String[] args) {

        EntityManagerFactory factory = EntityFactory.getEntityManager().getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        entityManager.createQuery("SELECT p FROM Project p ORDER BY p.name", Project.class)
                .setMaxResults(10).getResultList().forEach(project -> System.out.printf("Project name: %s%n " +
                                "\t Project Description: %s%n" +
                                "\t Project Start Date: %s%n" +
                                "\t Project End Date: %s%n", project.getName(),
                        project.getDescription(),project.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")),
                        project.getEndDate() == null ? "null" : project.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))));
    }
}
