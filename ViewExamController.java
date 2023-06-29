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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewExamController implements Initializable {
    @FXML
    private Button BackBtn;

    @FXML
    private TableView<Exam> examsTV;

    @FXML
    private TableColumn<Exam, String> name;

    @FXML
    private TableColumn<Exam, Integer> noOfQ;

    @FXML
    private TableColumn<Exam, Integer> passMark;

    @FXML
    private TableColumn<Exam, Boolean> YNQ;

    @FXML
    private TableColumn<Exam, Boolean> MCQ;

    @FXML
    private TableColumn<Exam, Boolean> FBQ;

    @FXML
    private TableColumn<Exam, String> teacher;
    private String loggedUsername;

    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        Stage stage = (Stage) BackBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("teacher.fxml"));
        Parent root = fxmlLoader.load();
        TeacherController controller = fxmlLoader.getController();
        controller.setUsername(loggedUsername);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Teacher page");
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Exam> examObs = FXCollections.observableArrayList();
        examObs.addAll(Utils.exams);
        name.setCellValueFactory(new PropertyValueFactory<Exam, String>("name"));
        noOfQ.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("numberOfQuestions"));
        passMark.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("minPassAverage"));
        YNQ.setCellValueFactory(new PropertyValueFactory<Exam, Boolean>("YesOrNo"));
        MCQ.setCellValueFactory(new PropertyValueFactory<Exam, Boolean>("Multiplechoice"));
        FBQ.setCellValueFactory(new PropertyValueFactory<Exam, Boolean>("Fillblank"));
        teacher.setCellValueFactory(new PropertyValueFactory<Exam, String>("tname"));
        examsTV.setItems(examObs);
    }
}
