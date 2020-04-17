package be.pxl.student.entity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length=34)
    private String IBAN;
    @Column(length=45)
    private String name;

    @OneToMany(mappedBy = "accountId")
    private List<Payment> paymentsFrom;

    @OneToMany(mappedBy = "counterAccountId")
    private List<Payment> paymentsTo;

    public int getId() { return id; }

    public String getIBAN() {
        return IBAN;
    }
    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Payment> getPaymentsFromAccount() {
        return paymentsFrom;
    }
    public List<Payment> getPaymentsToAccount() { return paymentsTo; }

    public void setPaymentsFromAccount(List<Payment> paymentsFrom) {
        this.paymentsFrom = paymentsFrom;
    }
    public void setPaymentsToAccount(List<Payment> paymentsTo) {
        this.paymentsTo = paymentsTo;
    }

    @Override
    public String toString() {
        return "Account{" +
                "IBAN='" + IBAN + '\'' +
                ", name='" + name + '\'' +
                ", paymentsFrom=[" + paymentsFrom.stream().map(Payment::toString).collect(Collectors.joining(",")) + '\'' +
                ", paymentsTo=[" + paymentsTo.stream().map(Payment::toString).collect(Collectors.joining(",")) + "]}";
    }
}
