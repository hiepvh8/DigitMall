package com.optimatech.digitmall.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerInforRequest {

    private String firstname;
    private String lastname;
    private String introduce;
    private String link;
    private Date birthday;

}
