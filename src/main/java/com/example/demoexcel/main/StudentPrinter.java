package com.example.demoexcel.main;

import com.example.demoexcel.data.Students;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.IOException;

public class StudentPrinter extends PrinterClass<Students> {

    @Override
    public void print(Students student, int rowCounter, File file) throws IOException {
        initWorkBook();
        initHeaderStyle();
        initCellStyle();
        ;

        if (workbook.getSheet("Students") == null) {
            createSheet();
        }

        firstRow = sheet.createRow(0);

        createHeader(firstRow);


        Row row = sheet.createRow(rowCounter);

        createCells(row, student);


        saveToFile(file);

        workbook = null;

    }


    @Override
    public void createHeader(Row row) {
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
    public void createCells(Row row, Students students) {
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

    private void createSheet() {

        sheet = workbook.createSheet("Students");
        sheet.setColumnWidth(0, 5000);
        sheet.setColumnWidth(1, 5000);


    }


}
