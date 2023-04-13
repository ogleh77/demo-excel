package com.example.demoexcel;

import com.example.demoexcel.data.Laptop;
import com.example.demoexcel.data.Students;
import com.example.demoexcel.main.LaptopPrinter;
import com.example.demoexcel.main.Model;
import com.example.demoexcel.main.StudentPrinter;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField name;
    private Stage stage;
    private File selectedFile;
    @FXML
    private ProgressBar progress;
    private boolean start = false;
    @FXML
    private CheckBox laptops;
    @FXML
    private CheckBox students;


    @FXML
    void exportHandler() {
        selectedFile = new File("/Users/mrd/Desktop/students.xls");


        if (start) {
            runner.restart();
        } else {
            runner.start();
            start = true;
        }


//        FileChooser fileChooser = new FileChooser();
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.xls)", "*.xls");
//        fileChooser.getExtensionFilters().add(extFilter);
//        selectedFile = fileChooser.showSaveDialog(stage);


    }

    @FXML
    void addHandler() {
        Students student = new Students(22, name.getText(), LocalDate.now().plusDays(44), 10 * 32);
        Model.insertStudents(student);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            stage = (Stage) name.getScene().getWindow();
        });

        runner.setOnSucceeded(e -> {
            System.out.println("Students is Done");
        });
        laptopService.setOnSucceeded(e -> {
            System.out.println("Latop is Done");
        });

    }


    private final Service<Void> service = new Service<>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() throws InterruptedException {
                    int rowCounter = 0;
                    ObservableList<Students> students = Model.students();
                    StudentPrinter printer = new StudentPrinter();

                    for (Students student : students) {
                        rowCounter++;
                        updateProgress(rowCounter, students.size());
                        try {

                            printer.print(student, rowCounter + 1, selectedFile);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        System.out.println(student.studentName());
                        Thread.sleep(1000);
                    }


                    return null;
                }
            };
        }
    };


    private final Service<Void> laptopService = new Service<>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() throws InterruptedException {
                    int rowCounter = 0;
                    ObservableList<Laptop> laptops = Model.Laptop();
                    LaptopPrinter printerLaptop = new LaptopPrinter();

                    for (Laptop laptop : laptops) {
                        rowCounter++;
                        updateProgress(rowCounter, laptops.size());

                        try {
                            printerLaptop.print(laptop, rowCounter + 1, selectedFile);
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        System.out.println(laptop.getlName());
                        Thread.sleep(1000);
                    }


                    return null;
                }
            };
        }
    };


    private final Service<Void> runner = new Service<>() {
        @Override
        protected Task<Void> createTask() {

            return new Task<>() {
                @Override
                protected Void call() {
                    ObservableList<Students> students = Model.students();

                    com.example.demoexcel.xlsfiles.StudentPrinter studentPrinter = new com.example.demoexcel.xlsfiles.StudentPrinter();

                    try {
                        studentPrinter.print(students, selectedFile);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
        }
    };
}