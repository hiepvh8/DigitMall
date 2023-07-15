package com.optimatech.digitmall.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "qualities")
public class Quality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "star")
    private Double star;

    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "id")
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sellerid", referencedColumnName = "id")
    @JsonBackReference
    private Seller seller;
}
