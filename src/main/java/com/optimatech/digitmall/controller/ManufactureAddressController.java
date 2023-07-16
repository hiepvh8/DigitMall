package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.model.entity.ManufactureAddress;
import com.optimatech.digitmall.services.ManufactureAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("manufactureaddresses")
public class ManufactureAddressController {
    @Autowired
    private final ManufactureAddressService manufactureAddressService;

    public ManufactureAddressController(ManufactureAddressService manufactureAddressService) {
        this.manufactureAddressService = manufactureAddressService;
    }

    @GetMapping("")
    public ResponseEntity<?> getManufactureAddressById(){
        List<ManufactureAddress> manufactureAddresses = manufactureAddressService.getAllManufactureAddress();
        return ResponseEntity.ok(manufactureAddresses);
    }
}
