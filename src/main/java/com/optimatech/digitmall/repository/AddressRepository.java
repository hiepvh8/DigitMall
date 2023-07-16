package com.optimatech.digitmall.repository;

import com.optimatech.digitmall.model.entity.Address;
import com.optimatech.digitmall.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {

//    @Modifying
//    @Query(value = "INSERT INTO address (country, district, wards, details) VALUES (:c, :d,:w, :de)",
//    nativeQuery = true)
//    void creatAddress(@Param("c") String c,
//                      @Param("d") String d,
//                      @Param("w") String w,
//                      @Param("de") String de);
    // JPQL không hỗ trợ truy vấn INSERT

    @Query("SELECT a FROM Address a WHERE a.customer = :customer")
    List<Address> findAllAddressByCustomer(@Param("customer") Customer customer);
}
