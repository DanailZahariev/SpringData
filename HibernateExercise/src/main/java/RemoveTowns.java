import entities.Address;
import entities.Employee;
import entities.EntityFactory;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {

        EntityManagerFactory factory = EntityFactory.getEntityManager().getEntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter town name: ");
        String townName = scanner.nextLine();

        entityManager.getTransaction().begin();
        setEmployeeAddresses(townName, entityManager);
        int count = deleteAddressesFromTowns(townName, entityManager);


        Town town = entityManager.createQuery("SELECT t FROM Town t WHERE t.name = :town", Town.class).
                setParameter("town", townName)
                .getSingleResult();
        entityManager.remove(town);

        System.out.printf("%d address in %s deleted", count, townName);
        entityManager.getTransaction().commit();


    }

    private static int deleteAddressesFromTowns(String townName, EntityManager entityManager) {
        List<Address> addresses = entityManager.createQuery("SELECT a FROM Address a WHERE a.town.name = :town_name", Address.class).
                setParameter("town_name", townName).getResultList();
        for (Address address : addresses) {
            entityManager.remove(address);
        }
        return addresses.size();
    }

    private static void setEmployeeAddresses(String townName, EntityManager entityManager) {
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e WHERE e.address.town.name = :town_name", Employee.class).
                setParameter("town_name", townName).getResultList();
        for (Employee employee : employees) {
            employee.setAddress(null);
            entityManager.persist(employee);
        }
    }
}
