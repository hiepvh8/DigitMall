package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.model.entity.Trademark;
import com.optimatech.digitmall.services.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trademarks")
public class TrademarkController {
    @Autowired
    private final TrademarkService trademarkService;

    public TrademarkController(TrademarkService trademarkService) {
        this.trademarkService = trademarkService;
    }
    @GetMapping("")
    public ResponseEntity<?> getAllTrademark(){
        List<Trademark> trademarks = trademarkService.getAllTrademark();
        return ResponseEntity.ok(trademarks);
    }
}
