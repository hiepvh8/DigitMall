package com.optimatech.digitmall.model.entity;


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
@NoArgsConstructor
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
    // chỗ này là chỗ của địa chỉ shop
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
}
