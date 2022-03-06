package entity._05BillPaymentSystem;


import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(targetEntity = BankAccount.class, mappedBy = "user")
    private Set<BankAccount> bankAccount;

    @OneToMany(targetEntity = CreditCard.class, mappedBy = "user")
    private Set<CreditCard> creditCards;

    @Column(name = "billing_details", columnDefinition = "TEXT")
    private String billingDetails;


    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<BankAccount> getBankAccount() {
        return Collections.unmodifiableSet(bankAccount);
    }

    public void setBankAccount(Set<BankAccount> bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Set<CreditCard> getCreditCards() {
        return Collections.unmodifiableSet(creditCards);
    }

    public void setCreditCards(Set<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }
}
