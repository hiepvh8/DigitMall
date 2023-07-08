package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.repository.AccountRepository;
import com.optimatech.digitmall.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    private final AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
