package com.optimatech.digitmall.services;

import com.optimatech.digitmall.model.entity.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerService {
    Optional<Seller> getSellerById(Long id);

    //Return list seller
    public List<Seller> getAllSellers();
}
