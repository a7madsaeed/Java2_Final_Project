package com.example.ahmad_120220184;

import javafx.css.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddQYesOrNoController {

    @FXML
    private TextField questiontext;

    @FXML
    private TextField questionimage;


    @FXML
    private TextField questionmark;

    @FXML
    private Button addQuestionBtn;

    @FXML
    private Button cancelAddQuestionbtn;
    @FXML
    private RadioButton trueBtn;

    @FXML
    private ToggleGroup options;

    @FXML
    private RadioButton falseBtn;
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
        int mark = 0;
        if (Utils.isNumeric(questionmark.getText())) {
            mark = Integer.parseInt(questionmark.getText());
        }
        String img = questionimage.getText();
        boolean correctAnswer = trueBtn.isSelected();
        if (text.length() > 0) {
            if (mark != 0) {
                if (trueBtn.isSelected() || falseBtn.isSelected()) {
                    Utils.addQuestionYesOrNo(text, mark, img, correctAnswer);
                    Stage stage = (Stage) cancelAddQuestionbtn.getScene().getWindow();
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
                    err.setText("Please Choose Question Correct Answer");
                }
            } else {
                err.setText("Please Provide Question Mark");
            }
        } else {
            err.setText("Please Provide Question Text");
        }
    }

    @FXML
    void cancelAddQuestion(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelAddQuestionbtn.getScene().getWindow();
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
