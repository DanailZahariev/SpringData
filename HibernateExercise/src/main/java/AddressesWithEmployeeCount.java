import entities.Address;
import entities.EntityFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AddressesWithEmployeeCount {
    public static void main(String[] args) {

        EntityManagerFactory factory = EntityFactory.getEntityManager().getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("SELECT a FROM Address a ORDER BY a.employees.size DESC ", Address.class)
                .setMaxResults(10).getResultList().forEach(address -> System.out.printf("%s, %s - %d employees %n",
                        address.getText(), address.getTown() == null ? "None" : address.getTown().getName(),
                        address.getEmployees().size()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
