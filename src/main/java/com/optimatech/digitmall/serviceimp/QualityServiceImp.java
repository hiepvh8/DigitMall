package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.exception.NotFoundException;
import com.optimatech.digitmall.model.entity.Product;
import com.optimatech.digitmall.model.entity.Quality;
import com.optimatech.digitmall.model.entity.Seller;
import com.optimatech.digitmall.repository.QualityRepository;
import com.optimatech.digitmall.services.ProductService;
import com.optimatech.digitmall.services.QualityService;
import com.optimatech.digitmall.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QualityServiceImp implements QualityService {
    @Autowired
    private final QualityRepository qualityRepository;
    @Autowired
    private  final ProductService productService;
    @Autowired
    private final SellerService sellerService;

    public QualityServiceImp(QualityRepository qualityRepository, ProductService productService, SellerService sellerService) {
        this.qualityRepository = qualityRepository;
        this.productService = productService;
        this.sellerService = sellerService;
    }
    //Danh giá sao cho product
    public void createQuatityByProduct(Long productId, Double star){
        Optional<Product> product = productService.getProductById(productId);
        if(product.isPresent()&& star>=0 && star <=5){
            Quality quality = new Quality();
            quality.setStar(star);
            quality.setProduct(product.orElse(null));
            qualityRepository.save(quality);
        }else{
            if(!product.isPresent()){
                throw new NotFoundException("Product không tồn tại");
            }else{
                throw new NotFoundException("Đánh giá phải trong khoảng từ 0-5 star");
            }
        }
    }

    //Danh giá sao cho seller
    public void createQuatityBySeller(Long sellerId, Double star){
        Optional<Seller> seller = sellerService.getSellerById(sellerId);

        if(seller.isPresent() && star>=0 && star <=5){
            Quality quality = new Quality();
            quality.setStar(star);
            quality.setSeller(seller.orElse(null));
            qualityRepository.save(quality);
        }else{
            if(!seller.isPresent()){
                throw new NotFoundException("Seller không tồn tại");
            }else{
                throw new NotFoundException("Đánh giá phải trong khoảng từ 0-5 star");
            }
        }
    }

    //Lấy ra Đánh giá trung bình product
    public double calculateAverageQualityWithProduct(Long productId) {
        List<Quality> qualities = qualityRepository.findByProductId(productId);
        if (!qualities.isEmpty()) {
            double totalStars = 0;
            for (Quality quality : qualities) {
                totalStars += quality.getStar();
            }
            return totalStars / qualities.size();
        } else {
            return 0.0; // Hoặc giá trị mặc định khác tùy thuộc vào yêu cầu của bạn
        }
    }

    //Lấy ra Đánh giá trung bình seller
    public double calculateAverageQualityWithSeller(Long sellerId) {
        List<Quality> qualities = qualityRepository.findBySellerId(sellerId);
        if (!qualities.isEmpty()) {
            double totalStars = 0;
            for (Quality quality : qualities) {
                totalStars += quality.getStar();
            }
            return totalStars / qualities.size();
        } else {
            return 0.0; // Hoặc giá trị mặc định khác tùy thuộc vào yêu cầu của bạn
        }
    }
}
