package main.service;

import main.data.Student;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentService {
    private final EntityManager em;

    public StudentService(EntityManager em) {
        this.em = em;
    }

    public List<Student> findAll() {
        return em.createNamedQuery("Student.findAll", Student.class).getResultList();
    }

    public void add(Student student) {
        em.getTransaction().begin();
        try {
            em.persist(student);
            student.getFaculty().getStudents().add(student);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println("Error adding student");
        }
    }

    public void delete(Student student) {
        em.getTransaction().begin();
        try {
            em.remove(student);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println("Can't delete");
        }
    }
}
