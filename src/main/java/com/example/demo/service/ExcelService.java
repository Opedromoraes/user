package com.example.demo.service;

import com.example.demo.domain.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final UserRepository repository;

    public void getEmailsCSV(HttpServletResponse response) {
        List<User> users = repository.findAll();

        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=Users_E-mails.xlsx";
            response.setHeader(headerKey, headerValue);

            HSSFSheet sheet = createSheet(workbook);
            createHeader(sheet);
            createRow(sheet, users);

            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();

        } catch (IOException e) {
            throw new RuntimeException("Error while generating the sheet: " + e.getMessage());
        }
    }

    public HSSFSheet createSheet(HSSFWorkbook workbook) {
        return workbook.createSheet();
    }

    public void createHeader(HSSFSheet sheet) {
        HSSFRow header = sheet.createRow(0);
        createCell(header, 0, "Name");
        createCell(header, 1, "E-mail");
    }

    public void createRow(HSSFSheet sheet, List<User> users) {
        HSSFRow row;
        int rowCounter = 1;

        for (User user : users) {
            row = sheet.createRow(rowCounter);
            createCell(row, 0, user.getName());
            createCell(row, 1, user.getEmail());
            rowCounter++;
        }
    }

    public void createCell(HSSFRow row, int index, String value) {
        HSSFCell cell = row.createCell(index);
        cell.setCellValue(value);
    }

}


