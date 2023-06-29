package com.example.ahmad_120220184;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {
    @FXML
    private Label studentsNum;

    @FXML
    private Label teachersNum;
    @FXML
    private Button back;
    private String loggedUsername;

    public void setUsername(String x) {
        this.loggedUsername = x;
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Parent root = fxmlLoader.load();
        AdminController controller = fxmlLoader.getController();
        controller.setUsername(loggedUsername);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Admin page");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int sCount = 0, tCount = 0;
        for (int i = 0; i < Utils.users.size(); i++) {
            if (Utils.users.get(i) instanceof Teacher) tCount++;
            else if (Utils.users.get(i) instanceof Student) sCount++;
        }
        studentsNum.setText(String.valueOf(sCount));
        teachersNum.setText(String.valueOf(tCount));
    }
}
