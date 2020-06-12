package Entities;

import Entities.Account;
import Entities.Label;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name="getAllPayments",
                query = "SELECT p FROM Payment p"),
})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private float amount;
    @Column(length=45)
    private String currency;
    private String detail;

    @JoinColumn(name = "accountId")
    @ManyToOne
    //@Column(nullable = false)
    private Account account;

    @JoinColumn(name = "counterAccountId")
    @ManyToOne
    //@Column(nullable = false)
    private Account counterAccount;

    @JoinColumn(name = "labelId")
    @ManyToOne
    private Label label;

    public Payment() { }

    public Payment(Date date, float amount, String currency, String detail) {
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.detail = detail;
    }

    public int getId() { return Id; }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    public Account getCounterAccount() { return counterAccount; }
    public void setCounterAccount(Account counterAccount) { this.counterAccount = counterAccount; }

    @Override
    public String toString() {
        return "{" +
                "date=" + date +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
