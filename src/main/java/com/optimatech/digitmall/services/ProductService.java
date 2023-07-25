package com.optimatech.digitmall.services;

import com.optimatech.digitmall.Enum.Business;
import com.optimatech.digitmall.model.dto.ProductDTO;
import com.optimatech.digitmall.model.dto.ProductResponse;
import com.optimatech.digitmall.model.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    //R
    public List<ProductResponse> getAllProducts();
    public List<Product> getTopSoldProducts();
//    public List<Product> getFlassaleProducts(double discountPercentage);

    //Danh sách sản phẩm mới về 3 ngày gần nhất
    public List<Product> getNewlyAddedProducts();

    // Tìm kiếm sản phẩm theo keyword
    public List<Product> searchProducts(String keyword);

    //So sanh san phẩm
    public List<String> compareProducts(List<Long> productIds);
    public Optional<Product> getProductById(Long id);


    public boolean isProductCodeExists(String productCode);

    public void createProduct(ProductDTO productDTO);

    //update product
    void updateProduct(Long productId, ProductDTO productDTO);

    //Update product by id with Status
    public void updateProductByIdWithStatus(Long productId, Business status);

    //Update product by id with Advertisement
    public void updateProductByIdWithAdvertisement(Long productId, Boolean advertisement);

    //D
    //Delete product by id
    public void deleteProductById(Long id);
}
