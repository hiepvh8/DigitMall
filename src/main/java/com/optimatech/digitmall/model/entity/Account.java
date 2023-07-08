package com.optimatech.digitmall.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.optimatech.digitmall.Enum.Enable;
import com.optimatech.digitmall.Enum.Role;
import com.optimatech.digitmall.model.dto.SignUp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



import java.time.LocalDateTime;
import java.util.Collection;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Table(name="account")
public class Account  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Account(SignUp accountRequest) {
        this.username = accountRequest.getUsername();
        this.password = accountRequest.getPassword();
        this.enable = Enable.INACTIVE;
        this.role = Role.USER;
        this.created = LocalDateTime.now();
        this.email = accountRequest.getEmail();
    }
}

