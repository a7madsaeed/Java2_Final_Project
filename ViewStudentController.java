package com.example.ahmad_120220184;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewStudentController implements Initializable {
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
    private String loggedUsername;
    @FXML
    private Button BackBtn;

    @FXML
    void Back(ActionEvent event) throws IOException {
        Stage stage = (Stage) BackBtn.getScene().getWindow();
        stage.close();

        String src = "", title = "";
        if (Utils.searchByName(loggedUsername) instanceof Admin || loggedUsername.equalsIgnoreCase("admin")) {
            src = "admin.fxml";
            title = "Admin page";
        } else if (Utils.searchByName(loggedUsername) instanceof Teacher) {
            src = "teacher.fxml";
            title = "Teacher page";
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(src));
        Parent root = fxmlLoader.load();
        if (src.equalsIgnoreCase("admin.fxml")) {
            AdminController controller = fxmlLoader.getController();
            controller.setUsername(loggedUsername);
        } else if (src.equalsIgnoreCase("teacher.fxml")) {
            TeacherController controller = fxmlLoader.getController();
            controller.setUsername(loggedUsername);
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.show();
    }

    public void setUsername(String x) {
        this.loggedUsername = x;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < Utils.users.size(); i++) {
            if (Utils.users.get(i) instanceof Student s) {
                students.add(s);
            }
        }
        ObservableList<Student> studentsObs = FXCollections.observableArrayList();
        studentsObs.addAll(students);
        studentUsername.setCellValueFactory(new PropertyValueFactory<Student, String>("username"));
        studentName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        studentUniId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("universityId"));
        studentGender.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));
        studentBirthday.setCellValueFactory(new PropertyValueFactory<Student, String>("birthday"));

        viewStudentsTV.setItems(studentsObs);
    }
}
