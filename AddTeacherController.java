package com.example.ahmad_120220184;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class AddTeacherController {
    private String loggedUsername;
    @FXML
    private TextField teacherPasswordTF;

    @FXML
    private TextField teacherNameTF;

    @FXML
    private DatePicker teacherBirthdayTF;

    @FXML
    private Button addTeacher;

    @FXML
    private RadioButton male;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton female;

    @FXML
    private ImageView nameErr;

    @FXML
    private ImageView usernameErr;

    @FXML
    private ImageView passwordErr;

    @FXML
    private ImageView genderErr;

    @FXML
    private TextField teacherUsernameTF;

    @FXML
    private ImageView birthdayErr;

    @FXML
    private Button cancelAddTeacher;

    @FXML
    private TextField teacherSpecialtyTF;


    @FXML
    private ImageView specialtyErr;

    @FXML
    private TextField teacherSalaryTF;

    @FXML
    private ImageView salaryErr;

    @FXML
    void addTeacher(ActionEvent event) throws IOException {
        genderErr.setVisible(false);
        usernameErr.setVisible(false);
        passwordErr.setVisible(false);
        nameErr.setVisible(false);
        birthdayErr.setVisible(false);
        specialtyErr.setVisible(false);
        salaryErr.setVisible(false);

        String username = teacherUsernameTF.getText();
        String password = teacherPasswordTF.getText();
        String name = teacherNameTF.getText();
        String birthday = String.valueOf(teacherBirthdayTF.getValue());
        String speciality = teacherSpecialtyTF.getText();
        String salaryTxt = teacherSalaryTF.getText();
        Gender gender = null;

        boolean isDuplicate = false;
        for (int i = 0; i < Utils.users.size(); i++) {
            if (Utils.users.get(i).getUsername().equals(username)) {
                isDuplicate = true;
                break;
            }
        }

        if (!username.isEmpty() && !isDuplicate) {
            if (password.length() >= 6) {
                if (!name.isEmpty()) {
                    if (!birthday.isEmpty() && Utils.isValidBirthday(birthday)) {
                        boolean valid = false;
                        if (male.isSelected() && !female.isSelected()) {
                            gender = Gender.male;
                        } else if (female.isSelected() && !male.isSelected()) {
                            gender = Gender.female;
                        } else if (male.isSelected() && female.isSelected()) {
                            genderErr.setVisible(true);
                        } else {
                            genderErr.setVisible(true);
                        }
                        if (male.isSelected() ^ female.isSelected()) {
                            valid = true;
                        }
                        if (valid) {
                            if (speciality.length() != 0) {
                                if (Utils.isNumeric(salaryTxt)) {
                                    double salary = Double.parseDouble(salaryTxt);
                                    Utils.addTeacher(username, password, name, birthday, gender, salary, speciality);

                                    Stage stage = (Stage) cancelAddTeacher.getScene().getWindow();
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
                                } else {
                                    salaryErr.setVisible(true);
                                }

                            } else {
                                specialtyErr.setVisible(true);
                            }

                        }

                    } else {
                        birthdayErr.setVisible(true);
                    }
                } else {
                    nameErr.setVisible(true);
                }
            } else {
                passwordErr.setVisible(true);
            }
        } else {
            usernameErr.setVisible(true);
        }

    }

    @FXML
    void cancelAddTeacher(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelAddTeacher.getScene().getWindow();
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

    public void setUsername(String x) {
        this.loggedUsername = x;
    }
}
