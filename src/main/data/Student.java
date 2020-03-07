package main.data;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select s from Student s")
})
public class Student {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Basic
    private String name;
    @Basic
    private double rating;
    @ManyToOne(optional = false)
    private Faculty faculty;

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
