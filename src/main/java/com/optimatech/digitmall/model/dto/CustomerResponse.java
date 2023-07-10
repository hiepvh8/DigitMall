package com.optimatech.digitmall.model.dto;

import com.optimatech.digitmall.Enum.Rankk;
import com.optimatech.digitmall.model.entity.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerResponse {
    private Long id;
    private String fullname;
    private String avt;
    private String bgr;
    private Date birthday;
    private String intro;
    private Rankk rankk;

    public CustomerResponse(Customer customer){
        this.id = customer.getId();
        this.fullname = customer.getFirstname() + " " + customer.getLastname();
        this.avt = customer.getAvatarImg();
        this.bgr = customer.getBackGroundImg();
        this.birthday = customer.getBirthday();
        this.intro = customer.getIntroduce();
        this.rankk = customer.getRankk();
    }

}
