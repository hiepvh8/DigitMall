package com.optimatech.digitmall.importdata;

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
@Table(name = "country-vn")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "countryname")
    private String countryName;
    @Column(name = "countrycode")
    private String countryCode;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<District> district;
}
