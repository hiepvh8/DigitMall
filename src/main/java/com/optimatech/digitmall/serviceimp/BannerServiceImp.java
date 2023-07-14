package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.model.entity.Banner;
import com.optimatech.digitmall.repository.BannerRepository;
import com.optimatech.digitmall.services.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImp implements BannerService {
    private final BannerRepository bannerRepository;
    public BannerServiceImp(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    //R
    //Return list Banner
    public List<Banner> getAllBanners(){
       return bannerRepository.findAll();
    }
}
