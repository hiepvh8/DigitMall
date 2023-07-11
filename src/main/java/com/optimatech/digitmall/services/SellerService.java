package com.optimatech.digitmall.services;

import com.optimatech.digitmall.model.entity.Seller;

import java.util.Optional;

public interface SellerService {
    Optional<Seller> getSellerById(Long id);
}
