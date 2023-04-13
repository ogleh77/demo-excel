package com.example.demoexcel.xlsfiles;


import com.example.demoexcel.data.Students;
import javafx.collections.ObservableList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

public class StudentPrinter implements PrinterClass<Students> {

    private Sheet sheet = null;
    private Workbook workbook;
    private static CellStyle headerCellStyle;
    private static CellStyle regulaCellStyle;
    int counter = 2;

    @Override
    public void print(ObservableList<Students> students, File file) throws IOException, InterruptedException {


        if (workbook == null) {
            workbook = new HSSFWorkbook();
            createSheet("Students");
            initCellStyle();
            initHeaderStyle();
            Row firstRow = sheet.createRow(0);

            createHeader(firstRow);
        }


        for (Students student : students) {

            Row row = sheet.createRow(counter);
            createValueCell(row, student);
            counter++;
            System.out.println("Printing " + student.studentName());
            Thread.sleep(100);
        }

        saveToFile(file, workbook);
    }

    @Override
    public void createHeader(Row row) {
        headerCell(row, headerCellStyle);

        Cell cell4 = row.createCell(3);
        cell4.setCellValue("Lap top name");
        cell4.setCellStyle(headerCellStyle);

        Cell cell5 = row.createCell(4);
        cell5.setCellValue("price");
        cell5.setCellStyle(headerCellStyle);
    }

    public static void headerCell(Row row, CellStyle headerCellStyle) {
        Cell cell = row.createCell(0);
        cell.setCellValue("Student name");
        cell.setCellStyle(headerCellStyle);

        Cell cell2 = row.createCell(1);
        cell2.setCellValue("Date joined");
        cell2.setCellStyle(headerCellStyle);

        Cell cell3 = row.createCell(2);
        cell3.setCellValue("Grade");
        cell3.setCellStyle(headerCellStyle);
    }

    @Override
    public void createValueCell(Row row, Students students) {
        Cell cell = row.createCell(0);
        cell.setCellValue(students.studentName());
        cell.setCellStyle(regulaCellStyle);

        Cell cell2 = row.createCell(1);
        cell2.setCellValue(students.dateJoined());
        cell2.setCellStyle(regulaCellStyle);

        Cell cell3 = row.createCell(2);
        cell3.setCellValue(students.grade());
        cell3.setCellStyle(regulaCellStyle);

    }


    @Override
    public void createSheet(String sheetName) {
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
            sheet.setColumnWidth(0, 5000);
            sheet.setColumnWidth(1, 5000);
            sheet.setColumnWidth(2, 5000);
            sheet.setColumnWidth(3, 5000);
            sheet.setColumnWidth(4, 5000);
        }
    }


    //---------------Helper methods----------------–


    protected Font getFont(boolean isBold, int size) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) size);
        font.setFontName("Verdana");
        font.setBold(isBold);


        return font;
    }

    protected void initHeaderStyle() {

        headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(getFont(true, 15));
    }


    protected void initCellStyle() {

        regulaCellStyle = workbook.createCellStyle();
        regulaCellStyle.setFont(getFont(false, 14));
    }
}