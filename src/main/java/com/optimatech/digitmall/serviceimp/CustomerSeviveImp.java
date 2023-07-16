package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.model.dto.AddressRequest;
import com.optimatech.digitmall.model.dto.CustomerInforRequest;
import com.optimatech.digitmall.model.dto.CustomerResponse;
import com.optimatech.digitmall.model.entity.Account;
import com.optimatech.digitmall.model.entity.Address;
import com.optimatech.digitmall.model.entity.Cart;
import com.optimatech.digitmall.model.entity.Customer;
import com.optimatech.digitmall.repository.AddressRepository;
import com.optimatech.digitmall.repository.CartRepository;
import com.optimatech.digitmall.repository.CustomerRepository;
import com.optimatech.digitmall.services.CustomerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerSeviveImp implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ImageServiceImp imageServiceImp;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;

    public CustomerSeviveImp(CustomerRepository customerRepository,
                             ImageServiceImp imageServiceImp,
                             CartRepository cartRepository,
                             AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.imageServiceImp = imageServiceImp;
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
    }

    public boolean updateInfor(CustomerInforRequest customerInforRequest, Long cusid){
        try {
            customerRepository.updateInfor(customerInforRequest.getFirstname(),
                    customerInforRequest.getLastname(),
                    customerInforRequest.getIntroduce(),
                    customerInforRequest.getLink(),
                    customerInforRequest.getBirthday(),
                    cusid
            );

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

    public String xemTruocAvatar( MultipartFile file){
        String url = "";
        try {
            url = imageServiceImp.createPathUrlForImage(file);
        } catch (Exception e){
            System.out.println(e.getMessage());

        }
        return url;
    }

    public void updateAvatar(Long cusid,String url){
        customerRepository.updateAvatar(url,cusid);
    }

    public boolean addCart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account accountPrincipal = (Account) authentication.getPrincipal();
        Long cusid = accountPrincipal.getCustomer().getId();
        if(accountPrincipal.getCustomer().getCart().equals("")){
            Cart cart = new Cart();
            cartRepository.save(cart);
            Optional<Customer> customer = customerRepository.findById(cusid);
            if(customer.isPresent()){
                customer.get().setCart(cart);
                customerRepository.save(customer.get());
                return true;
            }
        }
        return false;
    }

    public void createAddress(Long cusid, AddressRequest addressRequest){
        Address address = new Address(addressRequest);
        Optional<Customer> customer = customerRepository.findById(cusid);

        if(customer.isPresent()){
            if(address.getMainAddress()){ // kiểm tra nếu cust set địa chỉ làm địa chỉ chính
                List<Address> list = addressRepository.findAllAddressByCustomer(customer.get());
                for (Address a:list) {
                    if(a.getMainAddress()){
                        a.setMainAddress(false); // set lai địa chỉ cũ thành false để bắt đầu lưu thông
                                                // tin địa chỉ mới
                        addressRepository.save(a);
                        break;
                    }
                }
            }
            address.setCustomer(customer.get());
            addressRepository.save(address); // lưu địa chỉ mới
        }


    }
}


