package com.optimatech.digitmall.services;

import com.optimatech.digitmall.model.dto.ProductDTO;
import com.optimatech.digitmall.model.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAllProducts();
    public List<Product> getTopSoldProducts();
//    public List<Product> getFlassaleProducts(double discountPercentage);
    public Optional<Product> getProductById(Long id);


    public boolean isProductCodeExists(String productCode);

    public void createProduct(ProductDTO productDTO);

    //update product
    void updateProduct(Long productId, ProductDTO productDTO);

    //Update product by id with Business
    public void updateProductByIdWithBusiness(Long productId, ProductDTO productDTO);


    //D
    //Delete product by id
    public void deleteProductById(Long id);
}
