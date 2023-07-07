package com.optimatech.digitmall.importdata;

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
@NoArgsConstructor
@Table(name = "district-vn")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "districtname")
    private String districtName;
    @Column(name = "districtcode")
    private String districtCode;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "countryid", referencedColumnName = "id")
    @JsonBackReference
    private Country country;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Wards> wards;
}
