package main.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.data.Faculty;
import main.data.Student;
import main.service.FacultyService;
import main.service.StudentService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Controller {

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

        facultyList.setItems(FXCollections.observableList(fs.findAll()));
        facultyList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> studentTable.setItems(
                        FXCollections.observableArrayList(newValue.getStudents()))
        );
    }
}
