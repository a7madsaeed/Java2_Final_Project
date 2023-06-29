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

public class AddExamController {

    @FXML
    private Button addExamBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField noq;

    @FXML
    private TextField pass;

    @FXML
    private RadioButton yntrue;

    @FXML
    private ToggleGroup yn;

    @FXML
    private RadioButton ynfalse;

    @FXML
    private TextField ename;

    @FXML
    private RadioButton mctrue;

    @FXML
    private ToggleGroup mc;

    @FXML
    private RadioButton mcfalse;

    @FXML
    private RadioButton fbtrue;

    @FXML
    private ToggleGroup fb;

    @FXML
    private RadioButton fbfalse;
    @FXML
    private Label err;
    private String loggedUsername;

    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    @FXML
    void addExam(ActionEvent event) throws IOException {
        String name = ename.getText();
        boolean YesOrNo = yntrue.isSelected(), Multiplechoice = mctrue.isSelected(), Fillblank = fbtrue.isSelected();
        int numberOfQuestions = 0, minPassAverage = 0;
        if (Utils.isNumeric(noq.getText())) {
            numberOfQuestions = Integer.parseInt(noq.getText());
        }
        if (Utils.isNumeric(pass.getText())) {
            minPassAverage = Integer.parseInt(pass.getText());
        }
        int ynCount = 0, mcCount = 0, fbCount = 0;
        for (int i = 0; i < Utils.questions.size(); i++) {
            if (Utils.questions.get(i) instanceof YesOrNo) {
                ynCount++;
            } else if (Utils.questions.get(i) instanceof Multiplechoice) {
                mcCount++;
            } else if (Utils.questions.get(i) instanceof Fillblank) {
                fbCount++;
            }
        }
        boolean valid = false;
        if (YesOrNo && Multiplechoice && Fillblank && (ynCount + mcCount + fbCount) >= numberOfQuestions) {
            valid = true;
        } else if (YesOrNo && Multiplechoice && (ynCount + mcCount) >= numberOfQuestions) {
            valid = true;
        } else if (YesOrNo && Fillblank && (ynCount + fbCount) >= numberOfQuestions) {
            valid = true;
        } else if (Multiplechoice && Fillblank && (mcCount + fbCount) >= numberOfQuestions) {
            valid = true;
        } else if (YesOrNo && (ynCount) >= numberOfQuestions) {
            valid = true;
        } else if (Multiplechoice && (mcCount) >= numberOfQuestions) {
            valid = true;
        } else if (Fillblank && (fbCount) >= numberOfQuestions) {
            valid = true;
        }
        if (name.length() > 0) {
            if (numberOfQuestions > 0) {
                if (minPassAverage > 0) {
                    if (valid) {
                        Utils.addExam(name, numberOfQuestions, minPassAverage, YesOrNo, Multiplechoice,
                                Fillblank, (Teacher) Utils.searchByName(loggedUsername));
                        Stage stage = (Stage) cancelBtn.getScene().getWindow();
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
                        err.setText("Please Provide Valid Exam No. of Questions");
                    }

                } else {
                    err.setText("Please Provide Exam Pass Average");
                }
            } else {
                err.setText("Please Provide Exam No. of Questions");
            }
        } else {
            err.setText("Please Provide Exam Name");
        }
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
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
