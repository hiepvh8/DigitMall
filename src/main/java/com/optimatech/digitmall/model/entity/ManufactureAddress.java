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
@Table(name = "manufactureaddress")
public class ManufactureAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    private String shortname;

    @OneToMany(mappedBy = "manufactureaddress", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> product;
}

