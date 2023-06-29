package com.example.ahmad_120220184;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewExamResultDetailsController {
    private String loggedUsername;

    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    @FXML
    private Button BackBtn;

    @FXML
    private Label ename;

    @FXML
    private Label qnum;

    @FXML
    private Label qtext;

    @FXML
    private Label outof;

    @FXML
    private Label ya;

    @FXML
    private Label ca;

    @FXML
    private Label qmark;

    @FXML
    private Button nextBtn;
    int n;
    ExamResult e;

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

    @FXML
    void Next(ActionEvent event) throws IOException {
        if (n + 1 == e.getE().getNumberOfQuestions()) {
            Stage stage = (Stage) ename.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewExamResultsForStudent.fxml"));
            Parent root = fxmlLoader.load();
            ViewExamResultsForStudentController controller = fxmlLoader.getController();
            controller.setUsername(loggedUsername);
            controller.sendData();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("view Exam Results page page");
            stage.show();
        } else {
            Stage stage = (Stage) ename.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewExamResultDetails.fxml"));
            Parent root = fxmlLoader.load();
            ViewExamResultDetailsController controller = fxmlLoader.getController();
            controller.setUsername(loggedUsername);
            controller.sendData(e, n + 1);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("View Exam Result Details page");
            stage.show();
        }
    }

    public void sendData(ExamResult e, int n) {
        if (n + 1 == e.getE().getNumberOfQuestions()) {
            nextBtn.setText("Finish");
        }
        setEr(e);
        setN(n);
        ename.setText(e.getEname());
        qnum.setText("Q." + (n + 1));
        qtext.setText(e.getE().getQuestions(n).getName());
        qmark.setText(e.getE().getQuestions(n).getMark() + "pt");
        ya.setText("Your Answer: " + e.getStudentAnswers(n));
        String caS = "";
        if (e.getE().getQuestions(n) instanceof YesOrNo) {
            ca.setText("Correct Answer: " + ((YesOrNo) e.getE().getQuestions(n)).getCorrectAnswer());
            caS = String.valueOf(((YesOrNo) e.getE().getQuestions(n)).getCorrectAnswer());
        } else if (e.getE().getQuestions(n) instanceof Multiplechoice) {
            ca.setText("Correct Answer: " + ((Multiplechoice) e.getE().getQuestions(n)).getCorrectAnswer());
            caS = String.valueOf(((Multiplechoice) e.getE().getQuestions(n)).getCorrectAnswer());

        } else if (e.getE().getQuestions(n) instanceof Fillblank) {
            ca.setText("Correct Answer: " + ((Fillblank) e.getE().getQuestions(n)).getCorrectAnswer());
            caS = String.valueOf(((Fillblank) e.getE().getQuestions(n)).getCorrectAnswer());

        }
        String yaS = e.getStudentAnswers(n), markS = String.valueOf(e.getE().getQuestions(n).getMark());
        System.out.println(yaS);
        System.out.println(caS);
        if (yaS.equalsIgnoreCase(caS)) {
            outof.setText(markS + "/" + markS);
        } else {
            outof.setText(0 + "/" + markS);
        }

    }

    public void setN(int n) {
        this.n = n;
    }

    public void setEr(ExamResult e) {
        this.e = e;
    }
}
