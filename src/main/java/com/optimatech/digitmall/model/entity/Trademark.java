package com.optimatech.digitmall.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trademark")
public class Trademark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "trademarkname")
    private String trademarkName;

    @OneToMany(mappedBy = "trademark", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> product;

    @OneToMany(mappedBy = "trademark", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Voucher> vouchers;
}
