package com.example.ahmad_120220184;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class AddQmutlipleController {
    @FXML
    private TextField questiontext;

    @FXML
    private TextField questionimage;

    @FXML
    private TextField questionmark;

    @FXML
    private Button addQestionBtn;

    @FXML
    private Button cancelAddQBtn;

    @FXML
    private TextField a;

    @FXML
    private RadioButton ar;

    @FXML
    private ToggleGroup ca;

    @FXML
    private RadioButton br;

    @FXML
    private RadioButton cr;

    @FXML
    private RadioButton dr;

    @FXML
    private TextField b;

    @FXML
    private TextField c;

    @FXML
    private TextField d;

    @FXML
    private Label err;
    private String loggedUsername;

    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    @FXML
    void addQuestion(ActionEvent event) throws IOException {
        err.setText("");
        String text = questiontext.getText();
        String img = questionimage.getText();
        int mark = 0;
        if (Utils.isNumeric(questionmark.getText())) {
            mark = Integer.parseInt(questionmark.getText());
        }
        String correctAnswer = a.getText();
        if (br.isSelected()) {
            correctAnswer = b.getText();
        } else if (cr.isSelected()) {
            correctAnswer = c.getText();
        } else if (dr.isSelected()) {
            correctAnswer = d.getText();
        }
        String[] answers = new String[4];
        answers[0] = a.getText();
        answers[1] = b.getText();
        answers[2] = c.getText();
        answers[3] = d.getText();
        if (text.length() > 0) {
            if (mark > 0) {
                if (!a.getText().isEmpty() && !b.getText().isEmpty() && !c.getText().isEmpty() && !d.getText().isEmpty()) {
                    Utils.addQuestionMultipleChoice(text, mark, img, answers, correctAnswer);
                    Stage stage = (Stage) cancelAddQBtn.getScene().getWindow();
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
                } else {
                    err.setText("Please Provide All Question Options");
                }
            } else {
                err.setText("Please Provide Question Mark");
            }
        } else {
            err.setText("Please Provide Question Text");
        }
    }

    @FXML
    void cancelAddQ(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelAddQBtn.getScene().getWindow();
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
}
