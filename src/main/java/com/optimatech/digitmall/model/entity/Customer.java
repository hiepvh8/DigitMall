package com.optimatech.digitmall.model.entity;


import com.optimatech.digitmall.Enum.Rankk;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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


}
