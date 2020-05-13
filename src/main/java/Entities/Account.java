package Entities;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name="getByIBAN",
                query = "SELECT a FROM Account a WHERE a.iban = :iban"),
        @NamedQuery(name="getByName",
                query = "SELECT a FROM Account a WHERE a.name = :name"),
        @NamedQuery(name="getByNameOrIban",
                query = "SELECT a FROM Account a WHERE (a.name = :name OR a.iban = :iban)"),
})
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String iban;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
