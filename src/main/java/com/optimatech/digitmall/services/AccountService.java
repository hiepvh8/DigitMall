package com.optimatech.digitmall.services;

import com.optimatech.digitmall.model.dto.Registerdto;
import com.optimatech.digitmall.model.entity.Account;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class AccountService {
    public abstract Account registerUser(Registerdto registerdto);

    public abstract UserDetails loginAccount(String username);

    public abstract Account registerAccount(Registerdto registerdto);

    public abstract UserDetails loadUserByUsername(String username);
}
