package com.optimatech.digitmall.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String productName;
    private String productCode;
    private String price;
    private String disscount;
    private String quantity;
    private Long categoryId;
    private Long industryId;
    private Long trademarkId;
    private Long manufactureAddressId;
    private String introduce;
}

