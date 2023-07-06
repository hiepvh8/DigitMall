package com.optimatech.digitmall.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "productname")
    private String productName;
    @Column(name = "productcode")
    private String productCode;
    private String quantity; // số lượng hàng trong kho
    private String sold; // số lượng hàng đã bán
    private String price; // giá bán
    private String introduct; // giới thiệu sản phẩm


}
