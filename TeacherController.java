package com.example.ahmad_120220184;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherController {

    @FXML
    private Button Dashboard;

    @FXML
    private Label welcomeLbl;

    @FXML
    private Button SeclogoutBtn;

    @FXML
    private Button viewStudentBtn;

    @FXML
    private Button addStudentBtn;

    @FXML
    private Button viewQuestionBtn;


    @FXML
    private Button editQuestionBtn;

    @FXML
    private Button viewExamBtn;

    @FXML
    private Button addExamBtn;

    @FXML
    private Button viewExamResultBtn;
    @FXML
    private Button addQYesOrNoBtn;

    @FXML
    private Button addQMultipleBtn;
    @FXML
    private Button viewSEResultBtn;
    @FXML
    private Button addQFillBlankBtn;

    public void setUsername(String username) {
        welcomeLbl.setText(username);
    }

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
    void addExam(ActionEvent event) throws IOException {
        Stage stage = (Stage) addStudentBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addExam.fxml"));
        Parent root = fxmlLoader.load();

        AddExamController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("add Exam page");
        stage.show();
    }

    @FXML
    void addQYesOrNo(ActionEvent event) throws IOException {
        Stage stage = (Stage) addStudentBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addQYesorNo.fxml"));
        Parent root = fxmlLoader.load();

        AddQYesOrNoController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("add Yes or NO Question page");
        stage.show();
    }

    @FXML
    void addQFillBlank(ActionEvent event) throws IOException {
        Stage stage = (Stage) addStudentBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addQFillBlank.fxml"));
        Parent root = fxmlLoader.load();

        AddQFillBlankController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("add Fill The Blank Question page");
        stage.show();
    }

    @FXML
    void addQMultiple(ActionEvent event) throws IOException {
        Stage stage = (Stage) addStudentBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addQmutliple.fxml"));
        Parent root = fxmlLoader.load();

        AddQmutlipleController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("add Multiple Choice Question page");
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
    void viewExam(ActionEvent event) throws IOException {
        Stage stage = (Stage) addStudentBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewExam.fxml"));
        Parent root = fxmlLoader.load();
        ViewExamController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("view Exams page");
        stage.show();
    }

    @FXML
    void viewExamResult(ActionEvent event) throws IOException {
        Stage stage = (Stage) addStudentBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewExamResults.fxml"));
        Parent root = fxmlLoader.load();
        ViewExamResultsController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("view Exams Results page");
        stage.show();
    }

    @FXML
    void viewStudentExamResult(ActionEvent event) throws IOException {
        Stage stage = (Stage) addStudentBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewStudentExamResults.fxml"));
        Parent root = fxmlLoader.load();
        ViewStudentExamResultsController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("view Student Exams Results page");
        stage.show();
    }

    @FXML
    void viewQuestion(ActionEvent event) throws IOException {
        Stage stage = (Stage) addStudentBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewQuestion.fxml"));
        Parent root = fxmlLoader.load();

        ViewQuestionController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("view Questions page");
        stage.show();
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

}
