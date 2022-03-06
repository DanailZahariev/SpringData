package entity._05BillPaymentSystem;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    @Column(name = "expiration_month")
    private Integer expirationMonth;

    @Column(name = "expiration_year")
    private Integer expirationYear;

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "creditCard", optional = false)
    private BankAccount bankAccount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CreditCard(String type, Integer expirationMonth, Integer expirationYear, User user, BankAccount bankAccount) {
        this.type = type;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.user = user;
        this.bankAccount = bankAccount;
    }

    public CreditCard() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
