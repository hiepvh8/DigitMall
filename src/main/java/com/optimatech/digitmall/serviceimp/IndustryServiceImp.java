package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.model.dto.IndustryDTO;
import com.optimatech.digitmall.model.entity.Industry;
import com.optimatech.digitmall.repository.IndustryRepository;
import com.optimatech.digitmall.services.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndustryServiceImp implements IndustryService {
    @Autowired
    private final IndustryRepository industryRepository;

    public IndustryServiceImp(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    //Return list product
    public List<Industry> getAllIndustries() {
        return industryRepository.findAll();
    }

    //Return product by id
    public Optional<Industry> getIndustryById(Long id) {
        Optional<Industry> industry = industryRepository.findById(id);
        if(industry.isPresent()){
            return industry;
        }else {
            throw new NotFoundE
        }

    }

    //Create danh mục
    public Industry createIndustry(IndustryDTO industryDTO) {
        Industry industry = new Industry();
        industry.setIndustryName(industryDTO.getIndustryName());
        return industryRepository.save(industry);
    }

    //Update product
    public Industry updateIndustry(Industry industry) {
        return industryRepository.save(industry);
    }

    //Delete product by id
    public void deleteIndustry(Long id) {
        industryRepository.deleteById(id);
    }
}
