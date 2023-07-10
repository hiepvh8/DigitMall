package com.optimatech.digitmall.repository;

import com.optimatech.digitmall.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //Dùng JPQL
    @Query("UPDATE Customer SET  firstname = :firstname, lastname = :lastname, introduce = :introduce, birthday = : birthday ")
    void updateInfor(@Param("firstname") String firstname,
                            @Param("lastname") String lastname,
                            @Param("introduce") String introduct,
                            @Param("birthday")Date date);


    @Query("UPDATE Customer SET avatarImg  = :avturl")
    void updateAvatar(@Param("avturl") String url);

    @Query("UPDATE Customer SET backGroundImg = :url")
    void updateBackGroundImg(@Param("url") String url);

    @Query("SELECT c FROM Customer c WHERE c.id = :cusid")
    Optional<Customer> findById(@Param("cusid") Long id);



}
