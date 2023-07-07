package com.optimatech.digitmall.repository;

import com.optimatech.digitmall.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
//    static Optional<Account> findByUsername(String usenname) {
//        return null;
//    }
}
