package com.example.ahmad_120220184;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditQMCController implements Initializable {

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
    private TextField a;

    @FXML
    private RadioButton ar;

    @FXML
    private ToggleGroup cc;

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
    private String loggedUsername;
    private int id;

    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        for (int i = 0; i < Utils.questions.size(); i++) {
            if (Utils.questions.get(i) instanceof Multiplechoice) {
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
            if (mark != 0) {
                if (!a.getText().isEmpty() && !b.getText().isEmpty() && !c.getText().isEmpty() && !d.getText().isEmpty()) {
                    Multiplechoice y = new Multiplechoice(text, mark, img, answers, correctAnswer);
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
                    err.setText("Please Provide All Question Options");
                }
            } else {
                err.setText("Please Provide Question Mark");
            }
        } else {
            err.setText("Please Provide Question Text");
        }
    }

    public void sendData(Question q) {
        id = q.getQid();
        qText.setText(q.getName());
        qImg.setText(q.getImg());
        qMark.setText(String.valueOf(q.getMark()));
        Multiplechoice e = (Multiplechoice) q;
        String[] answers = e.getAnswers();
        a.setText(answers[0]);
        b.setText(answers[1]);
        c.setText(answers[2]);
        d.setText(answers[3]);
        if (e.getCorrectAnswer().equals(a.getText())) {
            ar.setSelected(true);
        } else if (e.getCorrectAnswer().equals(b.getText())) {
            br.setSelected(true);
        } else if (e.getCorrectAnswer().equals(c.getText())) {
            cr.setSelected(true);
        } else if (e.getCorrectAnswer().equals(d.getText())) {
            dr.setSelected(true);
        }
    }
}
