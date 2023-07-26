package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.exception.NotFoundException;
import com.optimatech.digitmall.model.entity.Seller;
import com.optimatech.digitmall.repository.SellerRepository;
import com.optimatech.digitmall.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<Seller> seller = sellerRepository.findById(id);
        if(seller.isPresent()){
            return seller;
        }else{
            throw new NotFoundException("Seller không tồn tại trong hệ thống");
        }
    }

    //Return List Seller
    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }

    @Override
    public void createSellerFromCustomer() {

    }

}
