package com.optimatech.digitmall.importdata;


import com.optimatech.digitmall.respone.Response;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

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

    @PostMapping("/init-data")
    public ResponseEntity<?> importVN(@RequestParam("file") MultipartFile file){
        List<String> colunm = new ArrayList<>();
        List<Country> res = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            String checkCountry = "-999";
            String checkDistrict = "-999";
            String checkWards = "-999";
            Set<Country> tinh = new HashSet<>();
            Set<District> huyen = new HashSet<>();
            Set<Wards> xa = new HashSet<>();
            int test = 0;
            int flag = 0; //
            for (Row row : sheet) {
                for (Cell cell : row) {
                    colunm.add(cell.toString());
                }
                if(!colunm.get(1).equals(checkCountry)){
                    Country country = new Country();
                    country.setCountryCode(colunm.get(1));
                    country.setCountryName(colunm.get(2));
                    tinh.add(country);
                    checkCountry = colunm.get(1);
                    test ++;
                    flag = 1; // đánh dấu rằng có 1 country mới được tạo
                }
                else flag = 0;
                if(!colunm.get(3).equals(checkDistrict)){

                    District district = new District();
                    district.setDistrictCode(colunm.get(3));
                    district.setDistrictName(colunm.get(4));
                    huyen.add(district);
                    checkDistrict = colunm.get(3);
                }
                if(!colunm.get(5).equals(checkWards)){ // điều kiện này có thể bỏ
                    Wards wards = new Wards();
                    wards.setWardsCode(colunm.get(5));
                    wards.setWardsName(colunm.get(6));
                    xa.add(wards);
                    checkWards = colunm.get(5);
                }
                colunm.removeAll(colunm); // refesh

            }
            res.addAll(tinh);
            System.out.println(test);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Response("Thành Công",res,"200",""), HttpStatus.OK);
    }


}
