package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.Enum.Role;
import com.optimatech.digitmall.model.entity.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    // kiểm tra quyền call api
    public boolean checkAuthor(Long cusId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account accountPrincipal = (Account) authentication.getPrincipal();
        if(accountPrincipal.getCustomer().getId().equals(cusId) ||
                accountPrincipal.getRole().equals(Role.ADMIN))
            return true;

        else return false;
    }

    public Long getCustomerId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account accountPrincipal = (Account) authentication.getPrincipal();
        return  accountPrincipal.getCustomer().getId();
    }
}
