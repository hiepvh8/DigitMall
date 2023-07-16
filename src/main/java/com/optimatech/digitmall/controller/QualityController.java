package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.services.QualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5500")
@RequestMapping("qualities")
public class QualityController {
    @Autowired
    private final QualityService qualityService;


    public QualityController(QualityService qualityService) {
        this.qualityService = qualityService;
    }


}
