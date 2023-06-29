package com.example.ahmad_120220184;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewExamResultsController implements Initializable {

    @FXML
    private Button BackBtn;

    @FXML
    private ComboBox comb;

    @FXML
    private TableView<ExamResult> examResTV;

    @FXML
    private TableColumn<ExamResult, String> name;

    @FXML
    private TableColumn<ExamResult, Integer> uniId;

    @FXML
    private TableColumn<ExamResult, Integer> mark;
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

    ObservableList<ExamResult> examRseObs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> examNamesObs = FXCollections.observableArrayList();
        for (int i = 0; i < Utils.exams.size(); i++) {
            examNamesObs.add(Utils.exams.get(i).getName());
        }
        comb.setItems(FXCollections.observableArrayList(examNamesObs));
        comb.setValue(examNamesObs.get(0));
        String QType = comb.getValue().toString();
        for (int i = 0; i < Utils.examResult.size(); i++) {
            if (Utils.examResult.get(i).getE().getName().equals(QType)) {
                examRseObs.add(Utils.examResult.get(i));
            }
        }

        name.setCellValueFactory(new PropertyValueFactory<ExamResult, String>("sname"));
        uniId.setCellValueFactory(new PropertyValueFactory<ExamResult, Integer>("uniId"));
        mark.setCellValueFactory(new PropertyValueFactory<ExamResult, Integer>("mark"));

        examResTV.setItems(examRseObs);
    }

    @FXML
    void setTable(ActionEvent event) {
        examRseObs.clear();
        String QType = comb.getValue().toString();
        for (int i = 0; i < Utils.examResult.size(); i++) {
            if (Utils.examResult.get(i).getE().getName().equals(QType)) {
                examRseObs.add(Utils.examResult.get(i));
            }
        }
        examResTV.setItems(examRseObs);
    }
}
