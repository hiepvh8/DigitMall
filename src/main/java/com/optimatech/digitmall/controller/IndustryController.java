package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.model.entity.Industry;
import com.optimatech.digitmall.services.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/industries")
public class IndustryController {
    @Autowired
    private final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllIndustry(){
    List<Industry> industries = industryService.getAllIndustries();
    return  ResponseEntity.ok(industries);
}
}
