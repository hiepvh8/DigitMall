package com.optimatech.digitmall.services;

public interface QualityService {
    //Thêm đánh giá sao cho sản phẩm
    public void createQuatityByProduct(Long productId, Double star);

    //Thêm đánh giá sao cho nhà bán hàng
    public void createQuatityBySeller(Long sellerId, Double star);

    //Lấy ra Đánh giá trung bình product
    public double calculateAverageQualityWithProduct(Long productId);

    //Lấy ra Đánh giá trung bình seller
    public double calculateAverageQualityWithSeller(Long sellerId);
}
