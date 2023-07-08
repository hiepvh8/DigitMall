package com.optimatech.digitmall.services;

import com.optimatech.digitmall.model.entity.Trademark;

import java.util.List;
import java.util.Optional;

public interface TrademarkService {
    public List<Trademark> getAllTrademark();
    public Optional<Trademark> getTrademarkById(Long id);
    public Trademark createTrademark(Trademark trademark);
    public Trademark updateTrademark(Trademark trademark);
    public void deleteTrademark(Long id);
}
