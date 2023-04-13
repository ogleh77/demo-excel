package com.example.demoexcel.controllers;

import com.example.demoexcel.data.Students;
import com.example.demoexcel.main.Model;
import com.example.demoexcel.printer.StudentWithLaptops;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void exportHandler() {
        ObservableList<Students>students= Model.students();

        StudentWithLaptops.exportStudentsToExcel(students);
    }
}
