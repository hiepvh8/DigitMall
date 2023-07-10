package com.optimatech.digitmall.importdata;


import com.optimatech.digitmall.repository.CountryRepository;
import com.optimatech.digitmall.repository.DistrictRepository;
import com.optimatech.digitmall.repository.WardsRepository;
import com.optimatech.digitmall.respone.Response;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("")
public class ExcelController {
    private final CountryRepository countryRepository;
    private final DistrictRepository districtRepository;
    private final WardsRepository wardsRepository;

    public ExcelController(CountryRepository countryRepository,
                           DistrictRepository districtRepository,
                           WardsRepository wardsRepository) {
        this.countryRepository = countryRepository;
        this.districtRepository = districtRepository;
        this.wardsRepository = wardsRepository;
    }

    // đã test ok
    //@PostMapping("/import-excel")
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

    //@PostMapping("/init-data")
    public ResponseEntity<?> importVN(@RequestParam("file") MultipartFile file){
        List<String> colunm = new ArrayList<>();
        List<Country> res = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            String checkCountry = "Mã tỉnh thành";
            String checkDistrict = "Mã quận huyện";
            String checkWards = "Mã xã phường";
            List<Country> tinh = new ArrayList<>();
            Set<District> huyen = new HashSet<>();
            Set<Wards> xa = new HashSet<>();
            Country reference = new Country();
            List<District> list = new ArrayList<>();
            List<Wards> li = new ArrayList<>();
            District ref = new District();
            District luu = new District();
            Country luuC = new Country();
            Wards re = new Wards();
            District dd = new District();
            Country c = new Country();
            int test = 0, unit = 0, jnit = 0;
            int tmp1 = 0, tmp2 = 0;
            int ff = 0, ffd = 0;
            int flag1 = 0, flag2 = 0; // f1 là cờ của country f2 là cờ của district
            for (Row row : sheet) {
                for (Cell cell : row) {
                    colunm.add(cell.toString());
                }
                if(!colunm.get(1).equals(checkCountry)){
                    Country country = new Country();
                    country.setCountryCode(colunm.get(1));
                    country.setCountryName(colunm.get(2));
                    reference = country; // ????
                    tinh.add(country);
                    countryRepository.save(reference);
                    checkCountry = colunm.get(1);
                    flag1 = 1; // đánh dấu rằng có 1 country mới được tạo

                }
                else flag1 = 0; // chưa có sự thay đổi tên tỉnh

                if(!colunm.get(3).equals(checkDistrict)){
                    if(flag1 == 1 ) {
                        if(reference.getCountryName().equals("Hà Nội")){
                            c = reference;
                            ff = 1;
                            luuC = c; // hà nội
                        }
                        else{
                            if(ff == 1) {
                                System.out.println(luuC.getCountryName());
                                luuC.setDistrict(list);
                                ff = 0;
                                luuC = reference; // hà giang
                            }
                            else {

                                System.out.println(luuC.getCountryName());
                                luuC.setDistrict(list);
                                luuC = reference; // cao bằng
                            }
                            for (District d : list) {
                                System.out.println(d.getDistrictName());
                                //
                            }
                            System.out.println("----------------------------------------");
                            list.removeAll(list);
                            flag1 = 0;
                            unit ++;
                        }

                    }
                    District district = new District();
                    district.setDistrictCode(colunm.get(3));
                    district.setDistrictName(colunm.get(4));
                    district.setCountry(luuC);
                    ref = district; // thằng ref này là một ẩn số ??
                    huyen.add(district);
                    checkDistrict = colunm.get(3);
                    list.add(district);

                    flag2 = 1; // đánh dấu 1 district mới được tạo
                    tmp1++;
                }
                else flag2 = 0;

                if(!colunm.get(5).equals(checkWards)){ // điều kiện này có thể bỏ
                    if(flag2 == 1 ) {

                        if(ref.getDistrictName().equals("Quận Ba Đình")){
                            dd = ref;
                            ffd = 1;
                            luu = dd; // ba đình
                        }
                        else{
                            if(ffd == 1) {
                                System.out.println(luu.getDistrictName());
                                luu.setWards(li);
                                districtRepository.save(luu);
                                ffd = 0;
                                luu = ref; // hoàn kiếm
                            }
                            else {
                                System.out.println(luu.getDistrictName());
                                luu.setWards(li);
                                districtRepository.save(luu);
                                luu = ref; // tây hồ
                            }
                            for (Wards wa : li) {
                                System.out.println(wa.getWardsName());
                            }
                            System.out.println("----------------------------------------");
                            li.removeAll(li);
                            flag2 = 0;
                            unit ++;
                        }

                    }
                    Wards wards = new Wards();
                    wards.setWardsCode(colunm.get(5));
                    wards.setWardsName(colunm.get(6));
                    wards.setDistrict(luu);
                    wardsRepository.save(wards);
                    re = wards;
                    xa.add(wards);
                    li.add(wards);
                    checkWards = colunm.get(5);


                    tmp2++;

                }

                colunm.removeAll(colunm); // refesh


            }
            luuC.setDistrict(list); // thêm danh sách các huyện cuối cùng
            countryRepository.save(luuC);
            ref.setWards(li); // thêm danh sách các xa vào huyện cuối cùng
            ref.setCountry(luuC);
            districtRepository.save(ref);
            res.addAll(tinh);

//            System.out.println(test);
//            System.out.println(unit);
//            System.out.println(jnit);
//            System.out.println(Integer.toString(tmp1) + " huyen");
//            System.out.println(Integer.toString(tmp2) + " xa");
//            for (Country c : tinh) {
//                System.out.println(c.getCountryName());
//            }
//            System.out.println("-------------------------------");



        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Response("Thành Công",res,"200",""), HttpStatus.OK);
    }

    //@GetMapping("/refesh-data")
    public ResponseEntity<?> refeshData(){
        Optional<Country> country = countryRepository.findById(64L);
        List<District> districts = districtRepository.findAll();

        for (District d : districts) {
            int check = d.getWards().size();
            if(check == 0){
                d.setCountry(country.get());
                districtRepository.save(d);
            }
        }
        return new ResponseEntity<>(districts,HttpStatus.OK);
    }
    //@GetMapping("/refesh-data2")
    public ResponseEntity<?> refeshData2(){
        Optional<Country> country = countryRepository.findById(64L);
        countryRepository.delete(country.get());
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/get-country/{country-id}")
    public ResponseEntity<?> getAddressById(@PathVariable("country-id") Long id){
        return new ResponseEntity<>(countryRepository.findById(id),HttpStatus.OK);
    }



}
