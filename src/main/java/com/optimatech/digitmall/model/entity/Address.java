package com.optimatech.digitmall.model.entity;


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
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country; // tỉnh/thành phố
    private String district; // quận/huyện
    private String wards; // xã/phường/ thị trấn
    private String details; // địa chỉ chi tiết số nhà,ngõ,ngách,đường,....

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    @JsonBackReference
    private Customer customer;
}
