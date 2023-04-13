package com.example.demoexcel.xlsfiles;

import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public interface PrinterClass<T> {


    void print(ObservableList<T> t, File file) throws IOException, InterruptedException;

    void createHeader(Row row);

    void createValueCell(Row row, T t);

    void createSheet(String sheetName);


    default void saveToFile(File file, Workbook workbook) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        workbook.close();

    }


}
