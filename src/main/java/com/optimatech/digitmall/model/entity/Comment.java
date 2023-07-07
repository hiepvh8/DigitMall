package com.optimatech.digitmall.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sendbycustid", referencedColumnName = "id")
    @JsonBackReference
    private Customer customer;
    private String body;
    @Column(name = "timeat")
    private LocalDateTime timeAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productid", referencedColumnName = "id")
    @JsonBackReference
    private Product product;

}
