package be.pxl.student.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private LocalDate date;
    private float amount;
    @Column(length=45)
    private String currency;
    private String detail;

    @ManyToOne
    @Column(nullable = false)
    private Account accountId;
    @ManyToOne
    @Column(nullable = false)
    private Account counterAccountId;
    @ManyToOne
    private Label labelId;


    public Payment() {
    }

    public Payment(LocalDate date, float amount, String currency, String detail) {
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.detail = detail;
    }

    public int getId() { return Id; }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
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
