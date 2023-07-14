package com.optimatech.digitmall.repository;

import com.optimatech.digitmall.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductCode(String productCode);

    //Lấy ra top 100 sản phẩm có lượng sold cao nhất và có trạng thái ONL
    @Query(value = "SELECT * FROM products WHERE status = 'ONL' ORDER BY sold DESC LIMIT 100", nativeQuery = true)
    List<Product> findTop100OnlineProductsBySold();
//    List<Product> findAllByDisscountGreaterThan(String discountPercentage);

    //Lấy ra sản phẩm mới thêm từ 3 ngày gần đây
    @Query(value = "SELECT * FROM products WHERE status = 'ONL' AND date >= :startDate ORDER BY products.date DESC",nativeQuery = true)
    List<Product> findNewlyAddedProducts(LocalDateTime startDate);
    //D

}
