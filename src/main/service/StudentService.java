package main.service;

import main.data.Faculty;
import main.data.Student;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final EntityManager em;

    public StudentService(EntityManager em) {
        this.em = em;
    }

}
