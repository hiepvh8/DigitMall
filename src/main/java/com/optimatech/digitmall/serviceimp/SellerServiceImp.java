package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.model.entity.Seller;
import com.optimatech.digitmall.repository.SellerRepository;
import com.optimatech.digitmall.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerServiceImp implements SellerService {
    @Autowired
    private final SellerRepository sellerRepository;

    public SellerServiceImp(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }
    @Override
    public Optional<Seller> getSellerById(Long id) {
        return sellerRepository.findById(id);
    }
}
