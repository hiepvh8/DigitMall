package com.optimatech.digitmall.services;

import com.optimatech.digitmall.model.entity.ManufactureAddress;

import java.util.List;
import java.util.Optional;

public interface ManufactureAddressService {
    public List<ManufactureAddress> getAllManufactureAddress();
    public Optional<ManufactureAddress> getManufactureAddressById(Long id);
    public ManufactureAddress createManufactureAddress(ManufactureAddress manufactureAddress);
    public ManufactureAddress updateManufactureAddress(ManufactureAddress manufactureAddress);
    public void deleteManufactureAddress(Long id);
}
