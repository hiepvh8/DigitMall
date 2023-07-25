package com.optimatech.digitmall.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "backgroundimg")
    private String backGroundImg;
    private String logo;
    private String introduce;
    @Column(name = "shopname")
    private String shopName;
    private String hotline;
    //private Double quality;
    private String evaluate;
    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL)
    @JsonBackReference
    private Address address;
    // tạm thời trống

    @OneToOne
    @JoinColumn(name = "cusid", referencedColumnName = "id")
    @JsonManagedReference
    private Customer customer;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Banner> banner;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> products;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Quality> qualities;
    public Seller(){
        this.backGroundImg = "https://res.cloudinary.com/dxlgrtrvr/image/upload/v1690216078/mau-anh-bia-facebook-cua-uplevo-4_eqxj71.jpg";
        this.logo = "https://res.cloudinary.com/dxlgrtrvr/image/upload/v1690216068/unnamed_kohoso.png";
        this.shopName = "DigitMall-Tech";
        this.evaluate = "0";
    }
}
