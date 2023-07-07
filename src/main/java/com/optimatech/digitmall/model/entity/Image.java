package com.optimatech.digitmall.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.optimatech.digitmall.Enum.TypeImage;
import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @Enumerated(EnumType.STRING)
    private TypeImage type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productid", referencedColumnName = "id")
    @JsonBackReference
    private Product product;

}
