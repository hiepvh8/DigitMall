package com.optimatech.digitmall.model.entity;


import com.optimatech.digitmall.Enum.Enable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Enable enable;
    private LocalDateTime created;
    private String email;

    @Column(name = "phonenumber")
    private String phoneNumber;

}
