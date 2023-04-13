package com.example.demoexcel.main;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class PrinterClass<T> {
    protected static Workbook workbook;
    protected static CellStyle headerCellStyle;
    protected static CellStyle regulaCellStyle;
    protected static Sheet sheet;
    protected static Row firstRow = null;


    public abstract void createHeader(Row row);

    protected abstract void print(T t, int rowCounter, File file) throws IOException;


    public abstract void createCells(Row row, T t);



    protected void initWorkBook() {
        System.out.println("Work book called");

        if (workbook == null) {
            workbook = new HSSFWorkbook();
            System.out.println("Work book init");
        }
    }


    protected Font getFont(boolean isBold, int size) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) size);
        font.setFontName("Verdana");
        font.setBold(isBold);


        return font;
    }

    protected void initHeaderStyle() {
        if (headerCellStyle == null) {
            headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(getFont(true, 15));
        }
    }

    protected void initCellStyle() {
        if (regulaCellStyle == null) {
            regulaCellStyle = workbook.createCellStyle();
            regulaCellStyle.setFont(getFont(false, 14));
        }
    }

    protected void saveToFile(File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        workbook.close();

    }
}
