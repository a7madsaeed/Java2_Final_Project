package com.example.ahmad_120220184;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EditInfoController {
    @FXML
    private TextField studentPasswordTF;

    @FXML
    private TextField studentNameTF;

    @FXML
    private TextField studentBirthdayTF;

    @FXML
    private Button editStudentBtn;

    @FXML
    private RadioButton male;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton female;

    @FXML
    private TextField studentUsernameTF;

    @FXML
    private Label err;
    @FXML
    private Button cancelBtn;
    private String loggedUsername;
    int id;

    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    public void sendData(Student s) {
        id = s.getUid();
        studentUsernameTF.setText(s.getUsername());
        studentPasswordTF.setText(s.getPassword());
        studentNameTF.setText(s.getName());
        studentBirthdayTF.setText(s.getBirthday());
        if (s.getGender() == Gender.male) {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student.fxml"));
        Parent root = fxmlLoader.load();

        StudentController controller = fxmlLoader.getController();
        controller.setUsername(loggedUsername);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Student page");
        stage.show();
    }

    @FXML
    void editStudent(ActionEvent event) throws IOException {
        err.setText("");
        String username = studentUsernameTF.getText();
        String password = studentPasswordTF.getText();
        String name = studentNameTF.getText();
        String birthday = studentBirthdayTF.getText();
        Gender gender = null;
        if (male.isSelected()) {
            gender = Gender.male;
        } else {
            gender = Gender.female;

        }
        if (password.length() >= 6) {
            if (!name.isEmpty()) {
                if (!birthday.isEmpty() && Utils.isValidBirthday(birthday)) {
                    int index = 0;
                    for (int i = 0; i < Utils.users.size(); i++) {
                        if (Utils.users.get(i).getUsername().equals(loggedUsername)) {
                            index = i;
                        }
                    }
                    Student s = new Student(username, password, name, birthday, gender);
                    s.setUid(id);
                    Utils.users.set(index, s);
                    Stage stage = (Stage) cancelBtn.getScene().getWindow();
                    stage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student.fxml"));
                    Parent root = fxmlLoader.load();
                    StudentController controller = fxmlLoader.getController();
                    controller.setUsername(loggedUsername);

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setTitle("Student Pages");
                    stage.show();

                } else {
                    err.setText("Invalid Birthday");
                }
            } else {
                err.setText("Please Provide Name");
            }
        } else {
            err.setText("Password must be at least 6");
        }
    }
}
