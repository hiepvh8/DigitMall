package com.optimatech.digitmall.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.optimatech.digitmall.model.entity.Image;
import com.optimatech.digitmall.model.entity.Product;
import com.optimatech.digitmall.model.entity.Video;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {

    //private String productId;
    @JsonProperty("productname")
    private String productName;
    @JsonProperty("productcode")
    private String productCode;
    private List<Image> images;
    private Video video;
    private String price;
    private String disscount;
    private String quantity;
    private String sold;
    private String location; // địa điểm đang chứa product (tỉnh trong address seller)
//    private Long categoryId;
//    private Long industryId;
//    private Long trademarkId;
   // private Long manufactureAddressId;
    //private Long sellerId;
    private String introduce;

    public ProductResponse(Product product){
        this.productName = product.getProductName();
        this.productCode = product.getProductCode();
        this.images = product.getImages();
        this.video = product.getVideo();
        this.price = product.getPrice();
        this.disscount = product.getDisscount();
        this.quantity = product.getQuantity();
        this.sold = product.getSold(); // số lượng đã bán
        this.location = product.getSeller().getAddress().getCountry();
    }
}
