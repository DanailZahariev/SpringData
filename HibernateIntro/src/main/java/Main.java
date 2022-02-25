import entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory school = Persistence.createEntityManagerFactory("school");
        EntityManager entityManager = school.createEntityManager();
        entityManager.getTransaction().begin();
        Student student = new Student("Dani");
        entityManager.persist(student);
        entityManager.getTransaction().commit();


    }
}
