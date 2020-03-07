package main.data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name = "Faculty.findAll", query = "select f from Faculty f")
})
public class Faculty {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Basic
    private String name;
    @OneToMany(mappedBy = "faculty")
    private Collection<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return name;
    }
}
