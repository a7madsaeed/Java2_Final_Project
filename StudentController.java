package com.example.ahmad_120220184;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentController {
    @FXML
    private Button Dashboard;

    @FXML
    private Label welcomeLbl;

    @FXML
    private Button SeclogoutBtn;

    @FXML
    private Button solveExamsBtn;

    @FXML
    private Button viewExamsResBtn;

    @FXML
    private Button viewRankBtn;

    public void setUsername(String username) {
        welcomeLbl.setText(username);
    }

    @FXML
    void Seclogout(ActionEvent event) throws IOException {
        Stage stage = (Stage) SeclogoutBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login page");
        stage.show();
    }

    @FXML
    void editInfo(MouseEvent event) throws IOException {
        Stage stage = (Stage) SeclogoutBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editInfo.fxml"));
        Parent root = fxmlLoader.load();
        EditInfoController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());
        controller.sendData((Student) Utils.searchByName(welcomeLbl.getText()));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login page");
        stage.show();
    }

    @FXML
    void solveExams(ActionEvent event) throws IOException {
        Stage stage = (Stage) solveExamsBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("solveExam.fxml"));
        Parent root = fxmlLoader.load();

        SolveExamController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());
        controller.sendData();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Solve Exam page");
        stage.show();
    }

    @FXML
    void viewExamsRes(ActionEvent event) throws IOException {
        Stage stage = (Stage) solveExamsBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewExamResultsForStudent.fxml"));
        Parent root = fxmlLoader.load();

        ViewExamResultsForStudentController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());
        controller.sendData();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("view Exam Results page");
        stage.show();
    }

    @FXML
    void viewRank(ActionEvent event) throws IOException {
        Stage stage = (Stage) solveExamsBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewRank.fxml"));
        Parent root = fxmlLoader.load();
        ArrayList<Student> students = new ArrayList<Student>();
        for (int i = 0; i < Utils.users.size(); i++) {
            int count = 0, totalMark = 0;
            double avg = 0.0;
            if (Utils.users.get(i) instanceof Student e) {
                students.add(e);
                for (int j = 0; j < Utils.examResult.size(); j++) {
                    if (Utils.examResult.get(j).getS() == e) {
                        totalMark += Utils.examResult.get(j).getMark();
                        count++;
                    }
                }
                try {
                    avg = totalMark / count;

                } catch (Exception ignored) {

                }
                e.setAvg(avg);
            }
        }
        // Sort the list of Student objects by average in descending order
        try {
            Collections.sort(students, new Comparator<Student>() {
                public int compare(Student s1, Student s2) {
                    return Double.compare(s2.getAvg(), s1.getAvg());
                }
            });
            // Print the top 5 students with the highest average
//            for (int i = 0; i < 5; i++) {
//                System.out.print((i + 1) + ". " + students.get(i).getName() + " | avg: ");
//                students.get(i).getAvg();
//                if (students.get(i).getAvg() == 0.0) {
//                    System.out.print("there is no data" + "\n");
//                } else {
//                    System.out.print(students.get(i).getAvg() + "\n");
//                }
//            }
        } catch (Exception ignored) {
        }
        ViewRankController controller = fxmlLoader.getController();
        controller.setUsername(welcomeLbl.getText());
        controller.sendData(students);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("view Rank page");
        stage.show();
    }
}
