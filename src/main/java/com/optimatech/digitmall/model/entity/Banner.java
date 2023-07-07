package com.optimatech.digitmall.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "banner")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bannername")
    private String bannerName;
    private LocalDateTime start;
    private LocalDateTime end;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sellerid", referencedColumnName = "id")
    @JsonBackReference
    private Seller seller;
}
