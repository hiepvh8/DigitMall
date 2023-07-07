package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.model.dto.Registerdto;
import com.optimatech.digitmall.model.entity.Account;
import com.optimatech.digitmall.repository.AccountRepository;
import com.optimatech.digitmall.services.AccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public class AccountServiceImp extends AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImp(AccountRepository accountRepository,PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Account registerUser(Registerdto registerdto) {
        return null;
    }

    @Override
    public UserDetails loginAccount(String username) {
        return null;
    }

    @Override
    public Account registerAccount(Registerdto registerdto) {
        String encodedPassword = passwordEncoder.encode(registerdto.getPassword());

        Account account = new Account();
        account.setUsername(registerdto.getUsername());
        account.setPassword(encodedPassword);
        account.setEmail(registerdto.getEmail());

        // Save the user to the database
        accountRepository.save(account);

        return account;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Implement this method to load UserDetails from the database based on the provided username
        // Return the UserDetails object
        // You can use the UserRepository to query the user by username

        // Example implementation:
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(), account.getPassword(), Collections.emptyList()
        );
    }
}
