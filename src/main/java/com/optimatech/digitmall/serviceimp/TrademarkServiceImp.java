package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.exception.NotFoundException;
import com.optimatech.digitmall.model.dto.TrademarkDTO;
import com.optimatech.digitmall.model.entity.Industry;
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
        Optional<Trademark> trademark = trademarkRepository.findById(id);
        if(trademark.isPresent()){
            return trademark;
        }else {
            throw new NotFoundException("Danh mục không tồn tại ");
        }
    }

    //Create product
    public Trademark createTrademark(TrademarkDTO trademarkDTO) {
        Trademark trademark = new Trademark();
        trademark.setTrademarkName(trademarkDTO.getTrademarkName());
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
