package com.optimatech.digitmall.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.optimatech.digitmall.Enum.Business;
import jakarta.persistence.*;
import jxl.write.DateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "productname")
    private String productName;
    @Column(name = "productcode")
    private String productCode;
    private LocalDateTime date; //ngày nhập sản phẩm
    private String quantity; // số lượng hàng trong kho
    private String sold; // số lượng hàng đã bán
    private String price; // giá bán
    private String introduct; // giới thiệu sản phẩm
    private String attention; // sự chú ý - chỉ số quan tâm sản phẩm
    private Boolean advertisement; // trạng thái quảng cáo
    @Enumerated(EnumType.STRING)
    private Business status ; // trạng thái kinh doanh của sản phẩm
    private String disscount; // phần trăm giảm giá

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonBackReference
    private Video video;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Hashtag> hashtags;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Quality> qualities;

    @ManyToOne
    @JoinColumn(name = "sellerid", referencedColumnName = "id")
    @JsonBackReference
    private Seller seller;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "categoryid", referencedColumnName = "id")
    @JsonBackReference
    private Category category;

    @ManyToOne
    @JoinColumn(name = "trademarkid", referencedColumnName = "id")
    @JsonBackReference
    private Trademark trademark;

    @ManyToOne
    @JoinColumn(name = "industryid", referencedColumnName = "id")
    @JsonBackReference
    private Industry industry;

    @ManyToOne
    @JoinColumn(name = "manufactureaddressid", referencedColumnName = "id")
    @JsonBackReference
    private ManufactureAddress manufactureaddress;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Image> images;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product-cart",joinColumns = @JoinColumn(name = "productid"),
            inverseJoinColumns = @JoinColumn(name = "cartid") )
    @JsonManagedReference

    private List<Cart> cart;

}
