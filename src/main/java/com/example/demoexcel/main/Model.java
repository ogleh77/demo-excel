package com.example.demoexcel.main;

import com.example.demoexcel.data.Laptop;
import com.example.demoexcel.data.Students;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Model {
    private static ObservableList<Students> students;

    public static ObservableList<Students> students() {
        if (students == null) {
            students = FXCollections.observableArrayList();
            for (int i = 0; i < 11; i++) {
                Students student = new Students(i, "Student " + i, LocalDate.now(), (Math.random() * 10));

                student.setLaptops(Laptop());
                students.add(student);

            }
        }

        System.out.println(students);
        return students;
    }

    public static ObservableList<Laptop> Laptop() {
        ObservableList<Laptop> laptops = FXCollections.observableArrayList();
        for (int i = 0; i < 3; i++) {
            laptops.add(new Laptop("Laptop " + i, 10 * i));
        }
        return laptops;
    }


    public static void insertStudents(Students student) {
        students.add(student);
        System.out.println(students);
    }

//private static ObservableList<Students>students
}
