package com.example.demoexcel.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Objects;

public final class Students {
    private final int id;
    private final String studentName;
    private final LocalDate dateJoined;
    private final double grade;
    private ObservableList<Laptop> laptops;

    public Students(int id, String studentName, LocalDate dateJoined, double grade) {
        this.id = id;
        this.studentName = studentName;
        this.dateJoined = dateJoined;
        this.grade = grade;
        this.laptops= FXCollections.observableArrayList();
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", dateJoined=" + dateJoined +
                ", grade=" + grade +
                '}';
    }

    public int id() {
        return id;
    }

    public String studentName() {
        return studentName;
    }

    public LocalDate dateJoined() {
        return dateJoined;
    }

    public double grade() {
        return grade;
    }

    public ObservableList<Laptop> getLaptops() {
        return laptops;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Students) obj;
        return this.id == that.id &&
                Objects.equals(this.studentName, that.studentName) &&
                Objects.equals(this.dateJoined, that.dateJoined) &&
                Double.doubleToLongBits(this.grade) == Double.doubleToLongBits(that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentName, dateJoined, grade);
    }

}
