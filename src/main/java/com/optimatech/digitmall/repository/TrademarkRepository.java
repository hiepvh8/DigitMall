package com.optimatech.digitmall.repository;

import com.optimatech.digitmall.model.entity.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrademarkRepository extends JpaRepository<Trademark, Long> {

}
