package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.model.entity.Trademark;
import com.optimatech.digitmall.repository.TrademarkRepository;
import com.optimatech.digitmall.services.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrademarkServiceImp implements TrademarkService {
    @Autowired
    private final TrademarkRepository trademarkRepository;

    public TrademarkServiceImp(TrademarkRepository trademarkRepository) {
        this.trademarkRepository = trademarkRepository;
    }

    //Return list product
    public List<Trademark> getAllTrademark() {
        return trademarkRepository.findAll();
    }

    //Return product by id
    public Optional<Trademark> getTrademarkById(Long id) {
        return trademarkRepository.findById(id);
    }

    //Create product
    public Trademark createTrademark(Trademark trademark) {
        return trademarkRepository.save(trademark);
    }

    //Update product
    public Trademark updateTrademark(Trademark trademark) {
        return trademarkRepository.save(trademark);
    }

    //Delete product by id
    public void deleteTrademark(Long id) {
        trademarkRepository.deleteById(id);
    }
}
