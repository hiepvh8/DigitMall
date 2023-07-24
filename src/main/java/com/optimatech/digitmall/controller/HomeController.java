package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.respone.Response;
import com.optimatech.digitmall.serviceimp.ProductServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Home", description = "trang chủ và các API điều hướng đi page khác ngoài internet")
@RequestMapping("/digitmaill")
public class HomeController {
    private final ProductServiceImp productServiceImp;

    public HomeController(ProductServiceImp productServiceImp) {
        this.productServiceImp = productServiceImp;
    }


    @GetMapping("/home")
    @CrossOrigin("http://localhost:5500")
    public ResponseEntity<?> getHome(){
        return new ResponseEntity<>(new Response("Thành Công",productServiceImp.getAllProducts(),
                "200", ""), HttpStatusCode.valueOf(200));
    }

}
