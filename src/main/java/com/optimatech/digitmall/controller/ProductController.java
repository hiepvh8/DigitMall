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

    //Return product by id
    @GetMapping("/{id}")
    public ResponseEntity<? > getProductById(@PathVariable Long  id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    //register product
    @PostMapping("/register")
    public ResponseEntity<? > createProduct(@RequestBody ProductDTO productDTO){
        productService.createProduct(productDTO);
        return new ResponseEntity<>("Thêm thành công!", HttpStatus.CREATED);
    }

    //Update product by id
    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        try {
            productService.updateProduct(productId, productDTO);
            return ResponseEntity.ok("Sản phẩm đã được cập nhật thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }
}
