package com.optimatech.digitmall.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.optimatech.digitmall.Enum.Rankk;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Seller seller;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartid", referencedColumnName = "id")
    @JsonManagedReference
    private Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Address> addressList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Bill> bills;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments;

}
