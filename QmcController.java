package com.example.ahmad_120220184;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class QmcController {
    @FXML
    private Label ename;

    @FXML
    private Label qnum;

    @FXML
    private Label qtext;

    @FXML
    private Label qmark;

    @FXML
    private RadioButton a;

    @FXML
    private ToggleGroup ans;

    @FXML
    private RadioButton b;

    @FXML
    private RadioButton c;

    @FXML
    private RadioButton d;

    @FXML
    private Button nextBtn;
    private String loggedUsername;
    int n, mark;
    Exam e;
    ArrayList<String> answers;

    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    @FXML
    void Next(ActionEvent event) throws IOException {
        if (n + 1 == e.getNumberOfQuestions()) {
            Multiplechoice y = (Multiplechoice) e.getQuestions(n);
            String ans = a.getText();
            if (b.isSelected()) {
                ans = b.getText();
            } else if (c.isSelected()) {
                ans = c.getText();
            } else if (d.isSelected()) {
                ans = d.getText();
            }
            if (y.getCorrectAnswer().equals(ans)) {
                mark += e.getQuestions(n).getMark();
            }
            answers.add(String.valueOf(ans));
            Utils.examResult.add(new ExamResult(e, (Student) Utils.searchByName(loggedUsername), mark, answers));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You got: " + mark + "/" + e.getTotalMarks());
            alert.showAndWait();
            Stage stage = (Stage) ename.getScene().getWindow();
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
        } else {
            Multiplechoice y = (Multiplechoice) e.getQuestions(n);
            String ans = a.getText();
            if (b.isSelected()) {
                ans = b.getText();
            } else if (c.isSelected()) {
                ans = c.getText();
            } else if (d.isSelected()) {
                ans = d.getText();
            }
            if (y.getCorrectAnswer().equals(ans)) {
                mark += e.getQuestions(n).getMark();
            }
            answers.add(String.valueOf(ans));
            Stage stage = (Stage) ename.getScene().getWindow();
            stage.close();
            String src = "";
            if (e.getQuestions(n + 1) instanceof YesOrNo) {
                src = "qyn.fxml";
            } else if (e.getQuestions(n + 1) instanceof Multiplechoice) {
                src = "qmc.fxml";
            } else if (e.getQuestions(n + 1) instanceof Fillblank) {
                src = "qfb.fxml";
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(src));
            Parent root = fxmlLoader.load();
            switch (src) {
                case "qyn.fxml" -> {
                    QynController controller = fxmlLoader.getController();
                    controller.setUsername(loggedUsername);
                    controller.sendData(e, n + 1, mark, answers);
                }
                case "qmc.fxml" -> {
                    QmcController controller = fxmlLoader.getController();
                    controller.setUsername(loggedUsername);
                    controller.sendData(e, n + 1, mark, answers);
                }
                case "qfb.fxml" -> {
                    QfbController controller = fxmlLoader.getController();
                    controller.setUsername(loggedUsername);
                    controller.sendData(e, n + 1, mark, answers);
                }
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Solve Exam page");
            stage.show();

        }
    }

    public void sendData(Exam e, int n, int mark, ArrayList<String> answers) {
        if (n + 1 == e.getNumberOfQuestions()) {
            nextBtn.setText("Submit");
        }
        setE(e);
        setN(n);
        setMark(mark);
        setAnswers(answers);
        ename.setText(e.getName());
        qtext.setText(e.getQuestions(n).getName());
        qmark.setText("(" + e.getQuestions(n).getMark() + ") pt");
        qnum.setText("Q." + (n + 1));
        a.setText(((Multiplechoice) e.getQuestions(n)).getAnswers(0));
        b.setText(((Multiplechoice) e.getQuestions(n)).getAnswers(1));
        c.setText(((Multiplechoice) e.getQuestions(n)).getAnswers(2));
        d.setText(((Multiplechoice) e.getQuestions(n)).getAnswers(3));

    }

    public void setN(int n) {
        this.n = n;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setE(Exam e) {
        this.e = e;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }
}
