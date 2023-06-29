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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SolveExamController {
    @FXML
    private ComboBox comb;

    @FXML
    private Button solveBtn;
    private String loggedUsername;

    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    @FXML
    private Button cancelBtn;

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
    void solve(ActionEvent event) throws IOException {
        String etype = comb.getValue().toString(), src = "", title = "";
        Exam e = Utils.searchByNameE(etype);
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        int n = 0, mark = 0;
        ArrayList<String> answers = new ArrayList<>();
        assert e != null;
        if (e.getQuestions(n) instanceof YesOrNo) {
            src = "qyn.fxml";
        } else if (e.getQuestions(n) instanceof Multiplechoice) {
            src = "qmc.fxml";
        } else if (e.getQuestions(n) instanceof Fillblank) {
            src = "qfb.fxml";
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(src));
        Parent root = fxmlLoader.load();

        switch (src) {
            case "qyn.fxml" -> {
                QynController controller = fxmlLoader.getController();
                controller.setUsername(loggedUsername);
                controller.sendData(e, n, mark, answers);
            }
            case "qmc.fxml" -> {
                QmcController controller = fxmlLoader.getController();
                controller.setUsername(loggedUsername);
                controller.sendData(e, n, mark, answers);
            }
            case "qfb.fxml" -> {
                QfbController controller = fxmlLoader.getController();
                controller.setUsername(loggedUsername);
                controller.sendData(e, n, mark, answers);
            }
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Solve Exam page");
        stage.show();
    }

    public boolean attemptExam(Exam e, Student s) {
        for (int i = 0; i < Utils.examResult.size(); i++) {
            if (Utils.examResult.get(i).getE() == e && Utils.examResult.get(i).getS() == s) {
                return true;
            }
        }
        return false;
    }

    public void sendData() {
        ObservableList<String> examNamesObs = FXCollections.observableArrayList();
        Student s = (Student) Utils.searchByName(loggedUsername);
        for (int i = 0; i < Utils.exams.size(); i++) {
            if (!attemptExam(Utils.exams.get(i), s)) {
                examNamesObs.add(Utils.exams.get(i).getName());
            }
        }
        comb.setItems(FXCollections.observableArrayList(examNamesObs));
    }
}
