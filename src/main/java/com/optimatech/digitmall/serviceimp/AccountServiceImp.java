package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.services.AccountService;

//
//
//import com.optimatech.digitmall.model.dto.Registerdto;
//import com.optimatech.digitmall.model.entity.Account;
//import com.optimatech.digitmall.repository.AccountRepository;
//import com.optimatech.digitmall.services.AccountService;
//import lombok.*;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Collections;
//
//@Getter
//@Setter
//@NoArgsConstructor
//
public class AccountServiceImp implements AccountService {
//    private final AccountRepository accountRepository;
//    private final PasswordEncoder passwordEncoder;
//    public AccountServiceImp(AccountRepository accountRepository, PasswordEncoder passwordEncoder){
//        this.accountRepository = accountRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//
//    public Account registerUser(Registerdto registerdto) {
//        return null;
//    }
//
//    public UserDetails loginAccount(String username) {
//        return null;
//    }
//
//    public Account registerAccount(Registerdto registerdto) {
//        String encodedPassword = passwordEncoder.encode(registerdto.getPassword());
//
//        Account account = new Account();
//        account.setUsername(registerdto.getUsername());
//        account.setPassword(encodedPassword);
//        account.setEmail(registerdto.getEmail());
//
//        // Save the user to the database
//        accountRepository.save(account);
//
//        return account;
//    }
//    public UserDetails loadUserByUsername(String username) {
//        // Implement this method to load UserDetails from the database based on the provided username
//        // Return the UserDetails object
//        // You can use the UserRepository to query the user by username
//
//        // Example implementation:
//        Account account = accountRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return new org.springframework.security.core.userdetails.User(
//                account.getUsername(), account.getPassword(), Collections.emptyList()
//        );
//
//    }
}
