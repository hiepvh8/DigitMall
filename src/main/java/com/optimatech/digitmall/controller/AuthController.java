package com.optimatech.digitmall.controller;


import com.optimatech.digitmall.model.dto.Logindto;
import com.optimatech.digitmall.model.dto.Registerdto;
import com.optimatech.digitmall.model.dto.Tokendto;
import com.optimatech.digitmall.model.entity.Account;
import com.optimatech.digitmall.security.JwtService;
import com.optimatech.digitmall.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AccountService accountService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController( AccountService accountService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.accountService = accountService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Registerdto registerdto) {
        Account newAccount = accountService.registerUser(registerdto);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Logindto logindto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(logindto.getUsername(), logindto.getPassword()));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = accountService.loadUserByUsername(logindto.getUsername());
        String token = jwtService.generateToken(userDetails);

        Tokendto tokendto = new Tokendto(token);
        return ResponseEntity.ok(tokendto);
    }
}
