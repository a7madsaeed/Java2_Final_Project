package com.example.ahmad_120220184;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditQFBController implements Initializable {
    @FXML
    private ListView<Integer> list;

    @FXML
    private TextField qText;

    @FXML
    private TextField qImg;

    @FXML
    private TextField qMark;

    @FXML
    private Button editBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label err;

    @FXML
    private TextField qAnswer;
    private String loggedUsername;
    private int id;

    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewQuestion.fxml"));
        Parent root = fxmlLoader.load();
        ViewQuestionController controller = fxmlLoader.getController();
        controller.setUsername(loggedUsername);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("View Questions page");
        stage.show();
    }

    @FXML
    void edit(ActionEvent event) throws IOException {
        err.setText("");
        String text = qText.getText();
        String img = qImg.getText();
        int mark = 0;
        if (Utils.isNumeric(qMark.getText())) {
            mark = Integer.parseInt(qMark.getText());
        }
        String correctAnswer = qAnswer.getText();
        if (text.length() > 0) {
            if (mark != 0) {
                if (correctAnswer.length() > 0) {
                    Fillblank y = new Fillblank(text, mark, img, correctAnswer);
                    y.setQid(id);
                    int index = 0;
                    for (int i = 0; i < Utils.questions.size(); i++) {
                        if (Utils.questions.get(i).getQid() == id) {
                            index = i;
                        }
                    }
                    Utils.questions.set(index, y);
                    Stage stage = (Stage) cancelBtn.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewQuestion.fxml"));
                    Parent root = fxmlLoader.load();
                    ViewQuestionController controller = fxmlLoader.getController();
                    controller.setUsername(loggedUsername);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setTitle("View Questions page");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        for (int i = 0; i < Utils.questions.size(); i++) {
            if (Utils.questions.get(i) instanceof Fillblank) {
                ids.add(Utils.questions.get(i).getQid());
            }
        }
        list.setItems(ids);
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                for (int i = 0; i < Utils.questions.size(); i++) {
                    if (list.getSelectionModel().getSelectedItem().equals(Utils.questions.get(i).getQid())) {
                        sendData(Utils.questions.get(i));
                    }
                }
            }
        });
    }

    public void sendData(Question q) {
        id = q.getQid();
        qText.setText(q.getName());
        qImg.setText(q.getImg());
        qMark.setText(String.valueOf(q.getMark()));
        Fillblank e = (Fillblank) q;
        qAnswer.setText(e.getCorrectAnswer());
    }
}
