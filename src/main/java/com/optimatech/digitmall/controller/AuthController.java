package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.model.dto.Login;
import com.optimatech.digitmall.model.dto.SignUp;
import com.optimatech.digitmall.model.entity.Account;
import com.optimatech.digitmall.repository.AccountRepository;
import com.optimatech.digitmall.respone.Response;
import com.optimatech.digitmall.respone.Token;
import com.optimatech.digitmall.security.JwtService;
import com.optimatech.digitmall.serviceimp.AccountServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AccountServiceImp accountService;

    public AuthController(AccountRepository accountRepository,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder,
                          AccountServiceImp accountService) {

        this.accountRepository = accountRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }
    //private final EmailService emailService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUp accountDto){
        if(accountService.createAccount(accountDto)){
            String jwt = jwtService.generateToken(new Account(accountDto));
            return new ResponseEntity<>(new Response("Thành Công",new Token(jwt),"200","")
                    , HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(new Response("Thất bại",null,"400",
                                    "Username hoặc email đã tồn tại!"),
                                    HttpStatus.valueOf(200));
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login accountRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                accountRequest.getUsername(), accountRequest.getPassword()
        ));
        Optional<Account> account = accountRepository.findByUsername(accountRequest.getUsername());
        if(account.isPresent()){
            String jwt = jwtService.generateToken(account.get());
            return new ResponseEntity<>(new Response("Thành công",new Token(jwt),"200",""),
                    HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(new Response("Thất bại",null,"400","Server không bao giờ lỗi \nLỗi do người dùng!OK"),
                HttpStatus.valueOf(200));

    }


}