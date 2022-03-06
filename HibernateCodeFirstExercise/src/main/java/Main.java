import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory("code-first-exercise")
                .createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.close();

    }
}
