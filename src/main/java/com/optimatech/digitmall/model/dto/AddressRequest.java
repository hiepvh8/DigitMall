package com.optimatech.digitmall.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {

    private String country;
    private String district;
    private String wards;
    private String details;
    @JsonProperty("mainaddress")
    private Boolean mainAddress;

}
