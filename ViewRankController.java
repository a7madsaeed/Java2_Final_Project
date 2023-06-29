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
import java.util.ArrayList;

public class ViewRankController {
    @FXML
    private Button BackBtn;
    @FXML
    private Label one;
    @FXML
    private Label two;
    @FXML
    private Label three;
    @FXML
    private Label four;
    @FXML
    private Label five;
    @FXML
    private Label avg1;

    @FXML
    private Label avg2;

    @FXML
    private Label avg3;

    @FXML
    private Label avg4;

    @FXML
    private Label avg5;
    private String loggedUsername;

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

    public void sendData(ArrayList<Student> s) {
        one.setText("1." + s.get(0).getName());
        avg1.setText(String.valueOf(s.get(0).getAvg()));
        two.setText("2." + s.get(1).getName());
        avg2.setText(String.valueOf(s.get(1).getAvg()));
        three.setText("3." + s.get(2).getName());
        avg3.setText(String.valueOf(s.get(2).getAvg()));
        four.setText("4." + s.get(3).getName());
        avg4.setText(String.valueOf(s.get(3).getAvg()));
        five.setText("5." + s.get(4).getName());
        avg5.setText(String.valueOf(s.get(4).getAvg()));
    }
}
