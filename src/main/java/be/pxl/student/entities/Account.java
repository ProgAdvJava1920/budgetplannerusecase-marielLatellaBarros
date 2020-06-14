package be.pxl.student.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name="getByIBAN",
                query = "SELECT a FROM Account a WHERE a.IBAN = :iban"),
        @NamedQuery(name="getByName",
                query = "SELECT a FROM Account a WHERE a.name = :name"),
        @NamedQuery(name="getByNameOrIban",
                query = "SELECT a FROM Account a WHERE (a.name = :name OR a.IBAN = :iban)"),
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 34)
    private String IBAN;
    @Column(length = 45)
    private String name;
    @OneToMany(mappedBy = "account")
    private List<Payment> payments = new ArrayList<>();

    public Account() { }

    public Account(String IBAN, String name) {
        this.IBAN = IBAN;
        this.name = name;
    }

    public Account(int id, String IBAN, String name) {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String iban) {
        this.IBAN = iban;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    //Same name and same account => same object
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(IBAN, account.IBAN) &&
                Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IBAN, name);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", IBAN='" + IBAN + '\'' +
                ", name='" + name + '\'' +
                ", payments=" + payments +
                '}';
    }
}
