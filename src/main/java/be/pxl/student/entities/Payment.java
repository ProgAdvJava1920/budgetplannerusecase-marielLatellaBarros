package be.pxl.student.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name="getAllPayments",
                query = "SELECT p FROM Payment p"),
})
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id; //PK


    private String IBAN;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private float amount;
    @Column(length=45)
    private String currency;
    private String detail;


    //private int accountId;//FK in database => change name
    @ManyToOne
    @JoinColumn(name = "accountId")
    @Column(nullable = false)
    private Account account;

    //private int counterAccountId;//FK in database => change name
    @ManyToOne
    @JoinColumn(name = "counterAccountId")
    @Column(nullable = false)
    private Account counterAccount;

//
//    @JoinColumn(name = "labelId")
//    @ManyToOne
//    private Label label;

    public Payment() { }

    public Payment(String IBAN, Date date, float amount, String currency, String detail) {
        this.IBAN = IBAN;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.detail = detail;
    }

    public int getId() { return Id; }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Id == payment.Id &&
                Float.compare(payment.amount, amount) == 0 &&
                Objects.equals(IBAN, payment.IBAN) &&
                Objects.equals(date, payment.date) &&
                Objects.equals(currency, payment.currency) &&
                Objects.equals(detail, payment.detail) &&
                Objects.equals(account, payment.account) &&
                Objects.equals(counterAccount, payment.counterAccount);
    }


    @Override
    public int hashCode() {
        return Objects.hash(Id, IBAN, date, amount, currency, detail, account, counterAccount);
    }

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
