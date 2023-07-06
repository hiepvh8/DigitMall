package com.optimatech.digitmall.importdata;


import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class ExcelController {
    // đã test ok
    @PostMapping("/import-excel")
    public ResponseEntity<?> excelTest(@RequestParam("file") MultipartFile file){
        List<String> data = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                for (Cell cell : row) {
                    data.add(cell.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
