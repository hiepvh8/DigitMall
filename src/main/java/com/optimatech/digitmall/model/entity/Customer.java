package com.optimatech.digitmall.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.optimatech.digitmall.Enum.Rankk;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "backgroundimg")
    private String backGroundImg;
    @Column(name = "avatarimg")
    private String avatarImg;
    private String introduce;
    private String firstname;
    private String lastname;
    private Date birthday;
    private String link;

    @Enumerated(EnumType.STRING)
    private Rankk rankk;
    private String coin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accoutid", referencedColumnName = "id")
    @JsonManagedReference
    private Account account;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference
    private Seller seller; // + chức năng tạo mới kênh người bán

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartid", referencedColumnName = "id")
    @JsonManagedReference
    private Cart cart; // + chức năng tạo mới 1 giỏ hàng

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Address> addressList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Bill> bills;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments;


    public Customer(){
        this.avatarImg = "https://res.cloudinary.com/dxlgrtrvr/image/upload/v1688734142/hinh-avatar-anh-dai-dien-FB-mac-dinh_pmvfhf.webp";
        this.backGroundImg = "https://res.cloudinary.com/dxlgrtrvr/image/upload/v1678860084/brgoud_nritsk.jpg";
        this.firstname = "Hello";
        this.lastname = "New Customer";
        this.coin = "50000";
        this.rankk = Rankk.BRONZE;
    }

}
