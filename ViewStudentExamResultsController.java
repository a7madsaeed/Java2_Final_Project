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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewStudentExamResultsController implements Initializable {
    private String loggedUsername;

    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    @FXML
    private TableView<ExamResult> studentResTV;

    @FXML
    private TableColumn<ExamResult, String> name;

    @FXML
    private TableColumn<ExamResult, Integer> mark;

    @FXML
    private Button BackBtn;

    @FXML
    private ListView<String> listview;

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

    ObservableList<ExamResult> studentRseObs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> names = FXCollections.observableArrayList();
        for (int i = 0; i < Utils.users.size(); i++) {
            if (Utils.users.get(i) instanceof Student) {
                names.add(Utils.users.get(i).getName());
            }
        }
        listview.setItems(names);
        listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                sendData(listview.getSelectionModel().getSelectedItem());
            }
        });
        name.setCellValueFactory(new PropertyValueFactory<ExamResult, String>("ename"));
        mark.setCellValueFactory(new PropertyValueFactory<ExamResult, Integer>("mark"));

    }

    public void sendData(String s) {
        studentRseObs.clear();

        for (int i = 0; i < Utils.examResult.size(); i++) {
            if (s.equals(Utils.examResult.get(i).getS().getName())) {
                studentRseObs.add(Utils.examResult.get(i));
            }
        }
        studentResTV.setItems(studentRseObs);
    }
}
