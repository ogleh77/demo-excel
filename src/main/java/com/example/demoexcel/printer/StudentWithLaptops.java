package com.example.demoexcel.printer;

import com.example.demoexcel.data.Laptop;
import com.example.demoexcel.data.Students;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class StudentWithLaptops {


    public static void exportStudentsToExcel(ObservableList<Students> students) {

        Workbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = (XSSFSheet) workbook.createSheet("Students");

        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Student name");
        header.createCell(1).setCellValue("date joined");
        header.createCell(2).setCellValue("grade");
        header.createCell(3).setCellValue("laptop name");
        header.createCell(4).setCellValue("price");
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.setZoom(150);
        int rowCount = 2;

        for (Students student : students) {

            if (!student.getLaptops().isEmpty()) {
                for (Laptop laptop : student.getLaptops()) {
                    XSSFRow row = sheet.createRow(rowCount);
                    row.createCell(0).setCellValue(student.studentName());
                    row.createCell(1).setCellValue(student.dateJoined().toString());
                    row.createCell(2).setCellValue(student.grade());
                    row.createCell(3).setCellValue(laptop.getlName());
                    row.createCell(4).setCellValue(laptop.getPrice());
                    rowCount++;
                }
                rowCount++;
            }else {

                XSSFRow row = sheet.createRow(rowCount);
                row.createCell(0).setCellValue(student.studentName());
                row.createCell(1).setCellValue(student.dateJoined().toString());

                row.createCell(2).setCellValue(student.grade());

                rowCount++;
            }
        }

        try {
            FileOutputStream stream = new FileOutputStream("data.xls");
            workbook.write(stream);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
