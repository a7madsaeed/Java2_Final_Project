package com.example.ahmad_120220184;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class AdminController {

    @FXML
    private Button addTeacherBtn;

    @FXML
    private TableView<Teacher> viewTeachersTV;

    @FXML
    private TableColumn<Teacher, String> teacherUsername;

    @FXML
    private TableColumn<Teacher, String> teacherName;

    @FXML
    private TableColumn<Teacher, String> teacherField;

    @FXML
    private TableColumn<Teacher, String> teacherGender;

    @FXML
    private TableView<Student> viewStudentsTV;

    @FXML
    private TableColumn<Student, String> studentUsername;

    @FXML
    private TableColumn<Student, String> studentName;

    @FXML
    private TableColumn<Student, Integer> studentUniId;

    @FXML
    private TableColumn<Student, String> studentGender;
    @FXML
    private TableColumn<Student, String> studentBirthday;

    @FXML
    private Label welcomeLbl;
    @FXML
    private Button Dashboard;
    @FXML
    private Button viewTeacherBtn;

    @FXML
    private Button viewStudentBtn;
    @FXML
    private Button addStudentBtn;

    @FXML
    private Button SeclogoutBtn;

    @FXML
    private Button Statistics;

    @FXML
    void Seclogout(ActionEvent event) throws IOException {
        Stage stage = (Stage) SeclogoutBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login page");
        stage.show();
    }

    @FXML
    void addStudent(ActionEvent event) throws IOException {
        Stage stage = (Stage) addStudentBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addStudent.fxml"));
        Parent root = fxmlLoader.load();
        AddStudentController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("view Student page");
        stage.show();
    }


    @FXML
    void addTeacher(ActionEvent event) throws IOException {
        Stage stage = (Stage) addStudentBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addTeacher.fxml"));
        Parent root = fxmlLoader.load();
        AddTeacherController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("view Student page");
        stage.show();
    }

    public void setUsername(String username) {
        welcomeLbl.setText(username);
    }

    @FXML
    void viewStudent(ActionEvent event) throws IOException {
        Stage stage = (Stage) addStudentBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewStudent.fxml"));
        Parent root = fxmlLoader.load();

        ViewStudentController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("view Student page");
        stage.show();
    }

    @FXML
    void viewTeacher(ActionEvent event) throws IOException {
        Stage stage = (Stage) addStudentBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewTeacher.fxml"));
        Parent root = fxmlLoader.load();

        ViewTeacherController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("view Teacher page");
        stage.show();
    }

    @FXML
    void Statistics(ActionEvent event) throws IOException {
        Stage stage = (Stage) addStudentBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("statistics.fxml"));
        Parent root = fxmlLoader.load();
        StatisticsController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("view Statistics page");
        stage.show();
    }
}
