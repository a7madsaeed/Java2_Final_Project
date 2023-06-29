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

public class ViewExamResultsForStudentController {
    @FXML
    private Button BackBtn;

    @FXML
    private TableView<ExamResult> examResTV;

    @FXML
    private TableColumn<ExamResult, String> exam;

    @FXML
    private TableColumn<ExamResult, Integer> mark;
    @FXML
    private TableColumn<ExamResult, Integer> outof;
    private String loggedUsername;

    public String getLoggedUsername() {
        return loggedUsername;
    }

    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        Stage stage = (Stage) BackBtn.getScene().getWindow();
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

    ObservableList<ExamResult> examRseObs = FXCollections.observableArrayList();


    public void sendData() {
        System.out.println(loggedUsername);
        exam.setCellValueFactory(new PropertyValueFactory<ExamResult, String>("ename"));
        mark.setCellValueFactory(new PropertyValueFactory<ExamResult, Integer>("mark"));
        outof.setCellValueFactory(new PropertyValueFactory<ExamResult, Integer>("totalMark"));
        System.out.println();
        for (int i = 0; i < Utils.examResult.size(); i++) {
            if (Utils.examResult.get(i).getS().getUsername().equals(loggedUsername)) {
                examRseObs.add(Utils.examResult.get(i));
            }
        }

        examResTV.setItems(examRseObs);
    }

    @FXML
    void viewDetails(MouseEvent event) throws IOException {
        ExamResult e = examResTV.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) BackBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewExamResultDetails.fxml"));
        Parent root = fxmlLoader.load();

        ViewExamResultDetailsController controller = fxmlLoader.getController();
        controller.setUsername(loggedUsername);
        controller.sendData(e, 0);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("View Exam Result Details page");
        stage.show();
    }
}
