package com.optimatech.digitmall.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customerid")
    private Long customerId;
    @Column(name = "sellerid")
    private Long sellerId;
}
