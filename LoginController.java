package com.example.ahmad_120220184;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button logoutBtn;

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField passwordTF;

    public String getUsernameTF() {
        return usernameTF.getText();
    }

    public void setUsernameTF(String usernameTF) {
        this.usernameTF.setText(usernameTF);
    }

    public String getPasswordTF() {
        return passwordTF.getText();
    }

    public void setPasswordTF(String passwordTF) {
        this.passwordTF.setText(passwordTF);
    }

    @FXML
    private Button loginBtn;
    @FXML
    private Label usernameError;

    @FXML
    private Label passwordError;

    @FXML
    void login(ActionEvent event) throws IOException {
//        setUsernameTF("s2");
//        setPasswordTF("123456");
        usernameError.setText("");
        passwordError.setText("");
        String username = usernameTF.getText().toLowerCase();
        String password = passwordTF.getText().toLowerCase();
        boolean ValidUsername = false, CorrectPassword = false;
        int UserType = -1;
        User loggedUser = null;
        for (int j = 0; j < Utils.users.size(); j++) {
            if (Utils.users.get(j).getUsername().equals(username)) {
                ValidUsername = true;
                if (Utils.users.get(j).getPassword().equals(password)) {
                    CorrectPassword = true;
                    loggedUser = Utils.users.get(j);
                    if (Utils.users.get(j) instanceof Admin) {
                        UserType = 0;
                    } else if (Utils.users.get(j) instanceof Teacher) {
                        UserType = 1;
                    } else if (Utils.users.get(j) instanceof Student) {
                        UserType = 2;
                    }
                }

            }
        }
        if (ValidUsername && CorrectPassword || username.equalsIgnoreCase("admin")
                && password.equalsIgnoreCase("admin")) {

            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.close();

            String title = "", resource = "";
            if (UserType == 0 || username.equalsIgnoreCase("admin")) {
                resource = "admin.fxml";
                title = "Admin";
            } else if (UserType == 1) {
                resource = "teacher.fxml";
                title = "Teacher";
            } else if (UserType == 2) {
                resource = "student.fxml";
                title = "Student";
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
            Parent root = fxmlLoader.load();
            if (resource.equalsIgnoreCase("admin.fxml")) {
                AdminController controller = fxmlLoader.getController();
                controller.setUsername(username);
            } else if (resource.equalsIgnoreCase("teacher.fxml")) {
                TeacherController controller = fxmlLoader.getController();
                controller.setUsername(username);
            } else if (resource.equalsIgnoreCase("student.fxml")) {
                StudentController controller = fxmlLoader.getController();
                controller.setUsername(username);
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(title + " page");
            stage.show();

        } else if (!ValidUsername && !username.equalsIgnoreCase("admin")) {
            usernameError.setText("username does not exist");
        } else {
            passwordError.setText("password incorrect");

        }
    }

    @FXML
    void logout(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }


}
