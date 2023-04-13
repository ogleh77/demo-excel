package com.example.demoexcel.xlsfiles;

import com.example.demoexcel.data.Students;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.IOException;

public class PrintStudentWithLaptops implements PrinterClass<Students> {


    @Override
    public void print(ObservableList<Students> t, File file) throws IOException, InterruptedException {

    }

    @Override
    public void createHeader(Row row) {
        row.createCell(0).setCellValue("");
    }

    @Override
    public void createValueCell(Row row, Students students) {

    }

    @Override
    public void createSheet(String sheetName) {

    }
}
