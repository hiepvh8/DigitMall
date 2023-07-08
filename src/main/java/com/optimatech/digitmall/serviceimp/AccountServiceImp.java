package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.Enum.Enable;
import com.optimatech.digitmall.model.dto.SignUp;
import com.optimatech.digitmall.model.entity.Account;
import com.optimatech.digitmall.model.entity.Customer;
import com.optimatech.digitmall.repository.AccountRepository;
import com.optimatech.digitmall.repository.CustomerRepository;
import com.optimatech.digitmall.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountServiceImp implements AccountService {
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    public AccountServiceImp(AccountRepository accountRepository, PasswordEncoder passwordEncoder, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
    }

    public Boolean ActiveAccount(Long id) {
        Optional<Account> acc = accountRepository.findById(id);
        if (acc.isPresent()) {
            Account findaccount = accountRepository.findById(id).get();
            findaccount.setEnable(Enable.ACTIVE);
            accountRepository.save(findaccount);
            return true;
        }
        return false;
    }

    public Long findIdByUsername(String usename) {
        Optional<Account> account = accountRepository.findByUsername(usename);
        if (account.isPresent()) {
            Account findAccount = accountRepository.findByUsername(usename).get();
            return findAccount.getId();
        }
        return 0L;
    }

    public List<Account> getListUser() {
        return accountRepository.findAll();
    }

    // check xem tên username đã tồn tại hay chưa
    public Boolean isUserNameValid(String username, String email) {
        if (username.equals("") || email.equals("")) return false;
        List<Account> list = getListUser();
        for (Account acc : list) {
            if (acc.getUsername().equals(username) || acc.getEmail().equals(email)) return false;
        }
        return true;
    }

    public Boolean createAccount(SignUp accountDto) {
        if (isUserNameValid(accountDto.getUsername(), accountDto.getEmail())) {
            Account newAccount = new Account(accountDto);
            newAccount.setPassword(passwordEncoder.encode(accountDto.getPassword()));
            Customer customer = new Customer();
            newAccount.setCustomer(customer);
            customer.setAccount(newAccount);
            customerRepository.save(customer);
            accountRepository.save(newAccount);

            return true;
        }
        return false;
    }
}




