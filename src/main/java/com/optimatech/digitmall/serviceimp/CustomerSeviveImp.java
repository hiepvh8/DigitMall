package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.model.dto.CustomerInforRequest;
import com.optimatech.digitmall.model.dto.CustomerResponse;
import com.optimatech.digitmall.model.entity.Customer;
import com.optimatech.digitmall.repository.CustomerRepository;
import com.optimatech.digitmall.services.CustomerService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class CustomerSeviveImp implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ImageServiceImp imageServiceImp;

    public CustomerSeviveImp(CustomerRepository customerRepository, ImageServiceImp imageServiceImp) {
        this.customerRepository = customerRepository;
        this.imageServiceImp = imageServiceImp;
    }

    public boolean updateInfor(CustomerInforRequest customerInforRequest){
        try {
            customerRepository.updateInfor(customerInforRequest.getFirstname(),
                    customerInforRequest.getLastname(),
                    customerInforRequest.getIntroduce(),
                    customerInforRequest.getBirthday());

        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public CustomerResponse getCustomer(Long cusId){

        Optional<Customer> c = customerRepository.findById(cusId);
        if(c.isPresent()){
            return new CustomerResponse(c.get());
        }
        return null; // not found

    }

    public boolean updateAvatar(@RequestParam("file") MultipartFile file){
        String url = "";
        try {
            url = imageServiceImp.createPathUrlForImage(file);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        customerRepository.updateAvatar(url);
        return true;
    }
}


