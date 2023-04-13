package com.example.demoexcel.main;

import com.example.demoexcel.data.Laptop;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.IOException;

public class LaptopPrinter extends PrinterClass<Laptop> {

    @Override
    public void print(Laptop laptop, int rowCounter, File file) throws IOException {
        initWorkBook();
        initHeaderStyle();
        initCellStyle();
        ;

        if (workbook.getSheet("Laptops") == null) {
            createSheet();
        }

        firstRow = sheet.createRow(0);

        createHeader(firstRow);


        Row row = sheet.createRow(rowCounter);

        createCells(row, laptop);


        saveToFile(file);
    }

    @Override
    public void createHeader(Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue("Laptop name");
        cell.setCellStyle(headerCellStyle);

        Cell cell2 = row.createCell(1);
        cell2.setCellValue("Laptop price");
        cell2.setCellStyle(headerCellStyle);
    }

    @Override
    public void createCells(Row row, Laptop laptop) {
        Cell cell = row.createCell(0);
        cell.setCellValue(laptop.getlName());
        cell.setCellStyle(headerCellStyle);

        Cell cell2 = row.createCell(1);
        cell2.setCellValue(laptop.getPrice());
        cell2.setCellStyle(headerCellStyle);

    }

    private void createSheet() {
        sheet = workbook.createSheet("Laptops");
        sheet.setColumnWidth(0, 5000);
        sheet.setColumnWidth(1, 5000);
    }
}
