package com.example.demoexcel;

import com.example.demoexcel.data.Students;
import com.example.demoexcel.main.Model;
import com.example.demoexcel.main.PrinterClass;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField name;
    private Stage stage;
    private final File selectedFile = new File("data.xls");
    @FXML
    private ProgressBar progress;
    private boolean start = false;

    @FXML
    void exportHandler() {


//        FileChooser fileChooser = new FileChooser();
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.xls)", "*.xls");
//        fileChooser.getExtensionFilters().add(extFilter);
//        selectedFile = fileChooser.showSaveDialog(stage);

        if (selectedFile != null) {

            if (start) {
                service.restart();
                progress.progressProperty().bind(service.progressProperty());
            } else {
                service.start();
                progress.progressProperty().bind(service.progressProperty());
                start = true;
            }
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            stage = (Stage) name.getScene().getWindow();
        });

        service.setOnSucceeded(e -> {
            System.out.println("Done");
        });

        service.onFailedProperty().addListener(e -> System.out.println("Failed"));
    }


    private final Service<Void> service = new Service<>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() throws InterruptedException {
                    int rowCounter = 0;
                    ObservableList<Students> students = Model.students();

                    for (Students student : students) {
                        rowCounter++;
                        updateProgress(rowCounter, students.size());
                        PrinterClass.printStudents(student, rowCounter+1, selectedFile);


                        System.out.println(student.studentName());
                        Thread.sleep(1000);
                    }


                    return null;
                }
            };
        }
    };

}