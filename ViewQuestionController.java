package com.example.ahmad_120220184;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ViewQuestionController implements Initializable {
    @FXML
    private Button BackBtn;

    @FXML
    private ComboBox comb;

    @FXML
    private TableView<Question> questions;

    @FXML
    private TableColumn<Question, Integer> id;

    @FXML
    private TableColumn<Question, String> text;

    @FXML
    private TableColumn<Question, Integer> mark;
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

    ObservableList<Question> ynObs = FXCollections.observableArrayList();
    ObservableList<Question> mcObs = FXCollections.observableArrayList();
    ObservableList<Question> fbObs = FXCollections.observableArrayList();

    @FXML
    void editQuestion(MouseEvent event) throws IOException {
        Question q = questions.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) BackBtn.getScene().getWindow();
        stage.close();
        String QType = comb.getValue().toString(), src = "", title = "";
        switch (QType) {
            case "Yes Or No" -> {
                src = "editQYN.fxml";
                title = "Edit Yes Or No Question Page";
            }
            case "Multiple Choice" -> {
                src = "editQMC.fxml";
                title = "Edit Multiple Choice Question Page";
            }
            case "Fill The Blank" -> {
                src = "editQFB.fxml";
                title = "Edit Fill The Blank Question Page";
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(src));
        Parent root = fxmlLoader.load();

        switch (src) {
            case "editQYN.fxml" -> {
                EditQYNController controller = fxmlLoader.getController();
                controller.setUsername(loggedUsername);
                controller.sendData(q);
            }
            case "editQMC.fxml" -> {
                EditQMCController controller = fxmlLoader.getController();
                controller.setUsername(loggedUsername);
                controller.sendData(q);
            }
            case "editQFB.fxml" -> {
                EditQFBController controller = fxmlLoader.getController();
                controller.setUsername(loggedUsername);
                controller.sendData(q);
            }
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comb.setItems(FXCollections.observableArrayList("Yes Or No", "Multiple Choice", "Fill The Blank"));
        comb.setValue("Yes Or No");
        ArrayList<Question> yn = new ArrayList<>();
        ArrayList<Question> mc = new ArrayList<>();
        ArrayList<Question> fb = new ArrayList<>();
        for (int i = 0; i < Utils.questions.size(); i++) {
            if (Utils.questions.get(i) instanceof YesOrNo) {
                yn.add(Utils.questions.get(i));
            } else if (Utils.questions.get(i) instanceof Multiplechoice) {
                mc.add(Utils.questions.get(i));
            } else if (Utils.questions.get(i) instanceof Fillblank) {
                fb.add(Utils.questions.get(i));
            }
        }
        ynObs.addAll(yn);
        mcObs.addAll(mc);
        fbObs.addAll(fb);

        id.setCellValueFactory(new PropertyValueFactory<Question, Integer>("Qid"));
        text.setCellValueFactory(new PropertyValueFactory<Question, String>("name"));
        mark.setCellValueFactory(new PropertyValueFactory<Question, Integer>("mark"));

        questions.setItems(ynObs);
    }

    public void setTable(ActionEvent event) {
        String QType = comb.getValue().toString();
        switch (QType) {
            case "Yes Or No" -> questions.setItems(ynObs);
            case "Multiple Choice" -> questions.setItems(mcObs);
            case "Fill The Blank" -> questions.setItems(fbObs);
        }
    }
}
