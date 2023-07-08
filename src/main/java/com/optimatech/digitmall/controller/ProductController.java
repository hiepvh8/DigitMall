package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.model.dto.ProductDTO;
import com.optimatech.digitmall.model.entity.*;
import com.optimatech.digitmall.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Return list product
    @GetMapping("")
    public ResponseEntity<?> gettAllProduct(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<? > getProductById(@PathVariable Long  id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<? > createProduct(@RequestBody ProductDTO productDTO){
        productService.createProduct(productDTO);
        return new ResponseEntity<>("Thêm thành công!", HttpStatus.CREATED);
    }

}
