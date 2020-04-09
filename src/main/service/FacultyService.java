package main.service;

import main.data.Faculty;
import main.data.Student;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class FacultyService {
    private EntityManager em;

    public FacultyService(EntityManager em) {
        this.em = em;
    }

    public List<Faculty> findAll() {
        return em.createNamedQuery("Faculty.findAll", Faculty.class).getResultList();
    }

    public List<Student> findStudents(int facultyId) {
        return new ArrayList<>(
                em.createQuery("select f from Faculty f where f.id=:fid", Faculty.class).setParameter("fid", facultyId).getSingleResult().getStudents()
        );
    }

    public void addFaculty(String facultyName) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyName);
        em.getTransaction().begin();
        try {
            em.persist(faculty);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println("Can't add");
        }
    }

    public void deleteFaculty(Faculty faculty) {
        em.getTransaction().begin();
        try {
            em.remove(faculty);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println("Can't delete");
        }
    }
}
