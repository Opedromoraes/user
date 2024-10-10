package com.example.demo.controller.excel;

import com.example.demo.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExcelController implements IExcelController {

    private final ExcelService service;


    @Override
    public void getEmailsCSV(HttpServletResponse response) {

        service.getEmailsCSV(response);
    }
}
