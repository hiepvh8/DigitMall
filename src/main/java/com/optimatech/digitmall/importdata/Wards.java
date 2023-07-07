package com.optimatech.digitmall.importdata;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wards-vn")
public class Wards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "wardsname")
    private String wardsName;
    @Column(name = "wardscode")
    private String wardsCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "districtid", referencedColumnName = "id")
    @JsonBackReference
    private District district;
}
