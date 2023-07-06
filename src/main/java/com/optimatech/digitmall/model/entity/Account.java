package com.optimatech.digitmall.model.entity;


import com.optimatech.digitmall.Enum.Enable;
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
    private LocalDateTime created;
    private String email;
    private String phoneNumber;

}
