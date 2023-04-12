package com.example.demoexcel.main;

import com.example.demoexcel.data.Students;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;

public class PrinterClass {
    private static Workbook workbook;
    private static CellStyle headerCellStyle;
    private static CellStyle regulaCellStyle;
    private static Sheet sheet;
    private static Row firstRow = null;

    public static void printStudents(Students student, int rowCounter, File file) {
        if (workbook == null) {
            workbook = new HSSFWorkbook();
        }

        if (sheet == null) {
            sheet = workbook.createSheet("Students");
            sheet.setColumnWidth(0, 5000);
            sheet.setColumnWidth(1, 5000);
        }


        headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(getFont(true, 15));
        firstRow = sheet.createRow(0);
        createHeader(firstRow);

        if (regulaCellStyle == null) {
            regulaCellStyle = workbook.createCellStyle();
            regulaCellStyle.setFont(getFont(false, 14));
        }
        Row row = sheet.createRow(rowCounter);
        createCells(row, student);


        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void createHeader(Row row) {
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


    private static void createCells(Row row, Students student) {

        Cell cell = row.createCell(0);
        cell.setCellValue(student.studentName());
        cell.setCellStyle(regulaCellStyle);

        Cell cell2 = row.createCell(1);
        cell2.setCellValue(student.dateJoined().toString());
        cell2.setCellStyle(regulaCellStyle);

        Cell cell3 = row.createCell(2);
        cell3.setCellValue(student.grade());
        cell3.setCellStyle(regulaCellStyle);

    }


    private static Font getFont(boolean isBold, int size) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) size);
        font.setFontName("Verdana");
        font.setBold(isBold);


        return font;
    }


}
