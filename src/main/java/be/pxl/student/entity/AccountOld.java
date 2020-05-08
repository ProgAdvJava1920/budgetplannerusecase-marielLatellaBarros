//package be.pxl.student.entity;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@NamedQueries({
//        @NamedQuery(name="getAllAccounts",
//                query = "SELECT a FROM AccountOld a"),
//        @NamedQuery(name="getByIdIBAN",
//                query = "SELECT a FROM AccountOld a WHERE a.IBAN = :IBAN"),
//        @NamedQuery(name="getById",
//                query = "SELECT a FROM AccountOld a WHERE a.id = :id"),
//})
//public class AccountOld {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(length = 34)
//    private String IBAN;
//    @Column(length = 45)
//    private String name;
//
//    @OneToMany(mappedBy = "account")
//    private List<Payment> payments;
//
//    public int getId() { return id; }
//
//    public String getIBAN() { return IBAN; }
//    public void setIBAN(String IBAN) { this.IBAN = IBAN; }
//
//    public String getName() { return name; }
//
//    public void setName(String name) { this.name = name; }
//
//    public List<Payment> getPayments() { return payments; }
//    public void setPayments(List<Payment> payments) { this.payments = payments; }
//
//    @Override
//    public String toString() {
//        return "Account{" +
//                "id=" + id +
//                ", IBAN='" + IBAN + '\'' +
//                ", name='" + name + '\'' +
//                ", payments=" + payments +
//                '}';
//    }
//}