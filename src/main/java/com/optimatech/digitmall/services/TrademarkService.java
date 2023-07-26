package com.optimatech.digitmall.services;

import com.optimatech.digitmall.model.dto.TrademarkDTO;
import com.optimatech.digitmall.model.entity.Trademark;

import java.util.List;
import java.util.Optional;

public interface TrademarkService {
    public List<Trademark> getAllTrademark();
    public Optional<Trademark> getTrademarkById(Long id);
    public Trademark createTrademark(TrademarkDTO trademarkDTO);
    public Trademark updateTrademark(Trademark trademark);
    public void deleteTrademark(Long id);
}
