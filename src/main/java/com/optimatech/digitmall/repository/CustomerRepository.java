package com.optimatech.digitmall.repository;

import com.optimatech.digitmall.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
