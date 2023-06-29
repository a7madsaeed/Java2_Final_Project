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

public class ViewTeacherController implements Initializable {
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
    private TableColumn<Teacher, String> teacherBirthday;

    @FXML
    private Button BackBtn;
    private String loggedUsername;

    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        Stage stage = (Stage) BackBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Parent root = fxmlLoader.load();
        AdminController controller = fxmlLoader.getController();
        controller.setUsername(loggedUsername);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Admin page");
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (int i = 0; i < Utils.users.size(); i++) {
            if (Utils.users.get(i) instanceof Teacher t) {
                teachers.add(t);
            }
        }
        ObservableList<Teacher> teachersObs = FXCollections.observableArrayList();

        teachersObs.addAll(teachers);

        teacherUsername.setCellValueFactory(new PropertyValueFactory<Teacher, String>("username"));
        teacherName.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));
        teacherField.setCellValueFactory(new PropertyValueFactory<Teacher, String>("specialty"));
        teacherGender.setCellValueFactory(new PropertyValueFactory<Teacher, String>("gender"));
        teacherGender.setCellValueFactory(new PropertyValueFactory<Teacher, String>("gender"));
        teacherBirthday.setCellValueFactory(new PropertyValueFactory<Teacher, String>("birthday"));

        viewTeachersTV.setItems(teachersObs);
    }
}
