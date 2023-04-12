package com.example.demoexcel.main;

import com.example.demoexcel.data.Laptop;
import com.example.demoexcel.data.Students;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Model {
    public static ObservableList<Students> students() {
        ObservableList<Students> students = FXCollections.observableArrayList();
        for (int i = 0; i < 11; i++) {
            students.add(new Students(i, "Student " + i, LocalDate.now(), (Math.random() * 10)));
        }
        System.out.println(students);
        return students;
    }

    public static ObservableList<Laptop> Laptop() {
        ObservableList<Laptop> laptops = FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++) {
            laptops.add(new Laptop("Laptop " + i, 10 * i));
        }
        return laptops;
    }

//private static ObservableList<Students>students
}
