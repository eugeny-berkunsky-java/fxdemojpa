package main.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.data.Faculty;
import main.data.Student;
import main.service.FacultyService;
import main.service.StudentService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Controller {

    @FXML private TextField ratingField;
    @FXML private TextField nameField;
    @FXML private TextField facultyField;
    @FXML private TableColumn<Student, Integer> idColumn;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, Double> ratingColumn;
    @FXML private TableView<Student> studentTable;
    @FXML private ListView<Faculty> facultyList;

    private FacultyService fs;
    private StudentService studentService;

    public void initialize() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("fxdemoPU");
        EntityManager em = factory.createEntityManager();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        fs = new FacultyService(em);
        studentService = new StudentService(em);

        facultyList.setItems(FXCollections.observableList(fs.findAll()));
        facultyList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue!=null)
                         studentTable.setItems(FXCollections.observableArrayList(newValue.getStudents()));
                }
        );
    }

    public void addFaculty() {
        String faculty = facultyField.getText();
        fs.addFaculty(faculty);
        facultyField.clear();
        facultyList.setItems(FXCollections.observableList(fs.findAll()));
    }

    public void deleteFaculty() {
        Faculty faculty = facultyList.getSelectionModel().getSelectedItem();
        if (faculty!=null) {
            fs.deleteFaculty(faculty);
            facultyList.setItems(FXCollections.observableList(fs.findAll()));
        }
    }

    public void addStudent() {
        Faculty faculty = facultyList.getSelectionModel().getSelectedItem();
        if (faculty!=null) {
            Student student = new Student();
            student.setName(nameField.getText());
            student.setRating(Double.parseDouble(ratingField.getText()));
            student.setFaculty(faculty);
            studentService.add(student);
            facultyList.setItems(FXCollections.observableList(fs.findAll()));
            studentTable.setItems(FXCollections.observableArrayList(faculty.getStudents()));
            nameField.clear();
            ratingField.clear();
        }
    }

    public void deleteStudent() {
        Student student = studentTable.getSelectionModel().getSelectedItem();
        if (student!=null) {
            Faculty faculty = student.getFaculty();
            studentService.delete(student);
            facultyList.setItems(FXCollections.observableList(fs.findAll()));
            faculty.getStudents().remove(student);
            studentTable.setItems(FXCollections.observableArrayList(faculty.getStudents()));
        }
    }
}
