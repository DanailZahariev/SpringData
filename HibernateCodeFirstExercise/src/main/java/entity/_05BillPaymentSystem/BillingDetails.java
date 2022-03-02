package entity._05BillPaymentSystem;

import javax.persistence.*;

@Entity
@Table(name = "billing_details")
public class BillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String number;

    @OneToOne
    private User user;

    @OneToOne
    @JoinColumn(name = "bank_account_id",referencedColumnName = "id")
    private BankAccount bankAccount;

    @OneToOne
    @JoinColumn(name = "credit_card_id",referencedColumnName = "id")
    private CreditCard creditCard;

    public BillingDetails(String number, User user) {
        this.number = number;
        this.user = user;
    }

    public BillingDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
