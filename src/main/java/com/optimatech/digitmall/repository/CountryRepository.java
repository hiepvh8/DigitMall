package com.optimatech.digitmall.repository;

import com.optimatech.digitmall.importdata.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
