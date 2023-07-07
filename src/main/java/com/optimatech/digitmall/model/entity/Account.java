package com.optimatech.digitmall.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.optimatech.digitmall.Enum.Enable;
import com.optimatech.digitmall.Enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Table(name="account")

public class Account  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Enable enable;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime created;
    private String email;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonBackReference
    private Customer customer;

}
