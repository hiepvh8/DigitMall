package com.optimatech.digitmall.repository;

import com.optimatech.digitmall.model.entity.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityRepository extends JpaRepository<Quality, Long> {
    List<Quality> findByProductId(Long productId);

    List<Quality> findBySellerId(Long sellerId);
}
