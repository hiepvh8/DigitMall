package com.optimatech.digitmall.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.optimatech.digitmall.model.dto.AddressRequest;
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
    @Column(name = "mainaddress")
    private Boolean mainAddress; // có phải địa chỉ chính hay không

    @ManyToOne //  xóa 1 address thì customer không bị xóa theo
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    @JsonBackReference
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sellerid", referencedColumnName = "id")
    @JsonManagedReference
    private Seller seller;



    public Address(AddressRequest addressRequest){
        this.country = addressRequest.getCountry();
        this.district = addressRequest.getDistrict();
        this.wards = addressRequest.getWards();
        this.details = addressRequest.getDetails();
    }
}
