package be.pxl.student.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length=45)
    private String name;
    private String description;

    @OneToMany(mappedBy = "labelId")
    private List<Payment> payments;

    public int getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", payments=" + payments +
                '}';
    }
}
