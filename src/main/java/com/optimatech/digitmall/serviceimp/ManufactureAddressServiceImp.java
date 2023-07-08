package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.model.entity.ManufactureAddress;
import com.optimatech.digitmall.repository.ManufactureAddressRepository;
import com.optimatech.digitmall.services.ManufactureAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufactureAddressServiceImp implements ManufactureAddressService {
    @Autowired
    private final ManufactureAddressRepository manufactureAddressRepository;

    public ManufactureAddressServiceImp(ManufactureAddressRepository manufactureAddressRepository) {
        this.manufactureAddressRepository = manufactureAddressRepository;
    }

    //Return list product
    public List<ManufactureAddress> getAllManufactureAddress() {
        return manufactureAddressRepository.findAll();
    }

    //Return product by id
    public Optional<ManufactureAddress> getManufactureAddressById(Long id) {
        return manufactureAddressRepository.findById(id);
    }

    //Create product
    public ManufactureAddress createManufactureAddress(ManufactureAddress manufactureAddress) {
        return manufactureAddressRepository.save(manufactureAddress);
    }

    //Update product
    public ManufactureAddress updateManufactureAddress(ManufactureAddress manufactureAddress) {
        return manufactureAddressRepository.save(manufactureAddress);
    }

    //Delete product by id
    public void deleteManufactureAddress(Long id) {
        manufactureAddressRepository.deleteById(id);
    }
}
