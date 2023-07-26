package com.optimatech.digitmall.repository;

import com.optimatech.digitmall.model.entity.ManufactureAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufactureAddressRepository extends JpaRepository<ManufactureAddress, Long> {
}
