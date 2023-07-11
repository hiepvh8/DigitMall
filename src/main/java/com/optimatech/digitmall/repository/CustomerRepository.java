package com.optimatech.digitmall.repository;

import com.optimatech.digitmall.model.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Repository
@Transactional // quản lí sự kiện tương tác với database
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //Dùng JPQL
    @Modifying // thông báo function có khả năng làm thay đổi dữ liệu
    @Query("UPDATE Customer c SET  c.firstname = :firstname, c.lastname = :lastname," +
            " c.introduce = :introduce, c.link =:link, c.birthday =:birthday WHERE c.id =:cusid")
    void updateInfor(@Param("firstname") String firstname,
                            @Param("lastname") String lastname,
                            @Param("introduce") String introduct,
                            @Param("link") String link,
                            @Param("birthday") Date date,
                            @Param("cusid") Long id
                            );


    @Modifying
    @Query("UPDATE Customer c SET c.avatarImg  = :avturl WHERE c.id =:cusid")
    void updateAvatar(@Param("avturl") String url,@Param("cusid") Long id);

    @Modifying
    @Query("UPDATE Customer c SET c.backGroundImg = :url WHERE c.id =:cusid")
    void updateBackGroundImg(@Param("url") String url, @Param("cusid") Long id);

    @Query("SELECT c FROM Customer c WHERE c.id = :cusid")
    Optional<Customer> findById(@Param("cusid") Long id);



}
