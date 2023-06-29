package com.example.ahmad_120220184;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddStudentController {
    @FXML
    private TextField studentPasswordTF;

    @FXML
    private TextField studentUsernameTF;

    @FXML
    private TextField studentNameTF;

    @FXML
    private DatePicker studentBirthdayTF;

    @FXML
    private Button addStudent;

    @FXML
    private Button cancelAddStudent;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;


    @FXML
    private ImageView nameErr;

    @FXML
    private ImageView usernameErr;

    @FXML
    private ImageView passwordErr;

    @FXML
    private ImageView birthdayErr;

    @FXML
    private ImageView genderErr;
    private String loggedUsername;


    @FXML
    void addStudent(ActionEvent event) throws IOException {
        genderErr.setVisible(false);
        usernameErr.setVisible(false);
        passwordErr.setVisible(false);
        nameErr.setVisible(false);
        birthdayErr.setVisible(false);

        String username = studentUsernameTF.getText();
        String password = studentPasswordTF.getText();
        String name = studentNameTF.getText();
        String birthday = String.valueOf(studentBirthdayTF.getValue());
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
                            Utils.addStudent(username, password, name, birthday, gender);
                            Stage stage = (Stage) cancelAddStudent.getScene().getWindow();
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


    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    @FXML
    void cancelAddStudent(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelAddStudent.getScene().getWindow();
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


}
