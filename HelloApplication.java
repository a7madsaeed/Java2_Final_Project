package com.example.ahmad_120220184;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HelloApplication extends Application {

    public static void main(String[] args) {
        Utils.addUser();
        Utils.addQuestion();
        Utils.addExam();
//        Utils.addExamResults();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login page");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}