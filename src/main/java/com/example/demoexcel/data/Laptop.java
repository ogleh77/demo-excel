package com.example.demoexcel.data;

public class Laptop {
    private String lName;
    private double price;

    public Laptop(String lName, double price) {
        this.lName = lName;
        this.price = price;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "lName='" + lName + '\'' +
                ", price=" + price +
                '}'+"\n";
    }
}
