import entities.EntityFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ChangeCasing {
    public static void main(String[] args) {

        EntityManagerFactory factory = EntityFactory.getEntityManager().getEntityManagerFactory();

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Town t set t.name = upper(t.name) where length(t.name) <= 5")
                .executeUpdate();
        entityManager.getTransaction().commit();

    }
}
