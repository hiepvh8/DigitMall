package com.optimatech.digitmall.services;

import com.optimatech.digitmall.model.entity.Industry;

import java.util.List;
import java.util.Optional;

public interface IndustryService {
    public List<Industry> getAllIndustries();
    public Optional<Industry> getIndustryById(Long id);
    public Industry createIndustry(Industry industry);
    public Industry updateIndustry(Industry industry);
    public void deleteIndustry(Long id);
}
