package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.model.dto.ProductDTO;
import com.optimatech.digitmall.model.entity.*;
import com.optimatech.digitmall.repository.*;
import com.optimatech.digitmall.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private  final CategoryRepository categoryRepository;
    @Autowired
    private final IndustryRepository industryRepository;
    @Autowired
    private final TrademarkRepository trademarkRepository;
    @Autowired
    private final ManufactureAddressRepository manufactureAddressRepository;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final IndustryService industryService;
    @Autowired
    private final TrademarkService trademarkService;
    @Autowired
    private final ManufactureAddressService manufactureAddressService;

    public ProductServiceImp(ProductRepository productRepository, CategoryRepository categoryRepository, IndustryRepository industryRepository, TrademarkRepository trademarkRepository, ManufactureAddressRepository manufactureAddressRepository, CategoryService categoryService, IndustryService industryService, TrademarkService trademarkService, ManufactureAddressService manufactureAddressService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.industryRepository = industryRepository;
        this.trademarkRepository = trademarkRepository;
        this.manufactureAddressRepository = manufactureAddressRepository;
        this.categoryService = categoryService;
        this.industryService = industryService;
        this.trademarkService = trademarkService;
        this.manufactureAddressService = manufactureAddressService;
    }


    //Return list product
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //Return product by id
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    //Create product

    //Update product
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    //Delete product by id
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public boolean isProductCodeExists(String productCode) {
        // Truy vấn cơ sở dữ liệu để kiểm tra xem có sản phẩm nào có mã productCode tồn tại hay không
        Optional<Product> existingProduct = productRepository.findById((long) Integer.parseInt(productCode));
        return existingProduct.isPresent();
    }
    @Override
    public void createProduct(ProductDTO productDTO){
        // Kiểm tra xem categoryId có hợp lệ hay không
        Long categoryId = productDTO.getCategoryId();
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            // Trả về lỗi nếu categoryId không hợp lệ
        }

        // Kiểm tra xem trademarkId có hợp lệ hay không
        Long trademarkId = productDTO.getTrademarkId();
        Optional<Trademark> trademark = trademarkService.getTrademarkById(trademarkId);
        if (trademark == null) {
            // Trả về lỗi nếu trademarkId không hợp lệ
        }
        // Kiểm tra xem trademarkId có hợp lệ hay không
        Long industryId = productDTO.getIndustryId();
        Optional<Industry> industry = industryService.getIndustryById(industryId);
        if (industry == null) {
            // Trả về lỗi nếu trademarkId không hợp lệ
        }
        // Kiểm tra xem trademarkId có hợp lệ hay không
        Long manufactureAddressId = productDTO.getManufactureAddressId();
        Optional<ManufactureAddress> manufactureAddress = manufactureAddressService.getManufactureAddressById(manufactureAddressId);
        if (manufactureAddress == null) {
            // Trả về lỗi nếu trademarkId không hợp lệ
        }
        if (category.isPresent() && trademark.isPresent() && industry.isPresent() && manufactureAddress.isPresent()) {
            // Tạo đối tượng Product từ DTO
            Product product = new Product();
            product.setProductName(productDTO.getProductName());
//            if (isProductCodeExists(productDTO.getProductCode())) {
//                throw new RuntimeException("Mã productCode đã tồn tại");
//            } else {
                product.setProductCode(productDTO.getProductCode());
        //    }
            product.setPrice(productDTO.getPrice());
            product.setDisscount(productDTO.getDisscount());
            product.setQuantity(productDTO.getQuantity());
            product.setCategory(category.orElse(null));
            product.setTrademark(trademark.orElse(null));
            product.setIndustry(industry.orElse(null));
            product.setManufactureaddress(manufactureAddress.orElse(null));

            // Lưu sản phẩm vào cơ sở dữ liệu
            productRepository.save(product);

        }
    }
}
