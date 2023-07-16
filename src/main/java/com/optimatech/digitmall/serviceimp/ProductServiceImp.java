package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.Enum.Business;
import com.optimatech.digitmall.exception.NotFoundException;
import com.optimatech.digitmall.model.dto.ProductDTO;
import com.optimatech.digitmall.model.entity.*;
import com.optimatech.digitmall.repository.*;
import com.optimatech.digitmall.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
    private final SellerRepository sellerRepository;
    @Autowired
    private final KeywordRepository keywordRepository;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final IndustryService industryService;
    @Autowired
    private final TrademarkService trademarkService;
    @Autowired
    private final ManufactureAddressService manufactureAddressService;
    @Autowired
    private final SellerService sellerService;

    public ProductServiceImp(ProductRepository productRepository, CategoryRepository categoryRepository, IndustryRepository industryRepository, TrademarkRepository trademarkRepository, ManufactureAddressRepository manufactureAddressRepository, SellerRepository sellerRepository, KeywordRepository keywordRepository, CategoryService categoryService, IndustryService industryService, TrademarkService trademarkService, ManufactureAddressService manufactureAddressService, SellerService sellerService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.industryRepository = industryRepository;
        this.trademarkRepository = trademarkRepository;
        this.manufactureAddressRepository = manufactureAddressRepository;
        this.sellerRepository = sellerRepository;
        this.keywordRepository = keywordRepository;
        this.categoryService = categoryService;
        this.industryService = industryService;
        this.trademarkService = trademarkService;
        this.manufactureAddressService = manufactureAddressService;
        this.sellerService = sellerService;
    }

    //C
    //Create Product
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
        // Kiểm tra xem trademarkId có hợp lệ hay không
        Long sellerId = productDTO.getSellerId();
        Optional<Seller> seller = sellerService.getSellerById(sellerId);
        if (seller == null) {
            // Trả về lỗi nếu trademarkId không hợp lệ
        }
        if (category.isPresent() && trademark.isPresent() && industry.isPresent() && manufactureAddress.isPresent() && seller.isPresent()) {
            if (isProductCodeExists(productDTO.getProductCode())) {
                //throw new RuntimeException("Mã productCode đã tồn tại");
            } else {
                // Tạo đối tượng Product từ DTO
                Product product = new Product();
                product.setProductName(productDTO.getProductName());
                product.setProductCode(productDTO.getProductCode());
                product.setDate(LocalDateTime.now());
                product.setPrice(productDTO.getPrice());
                product.setDisscount(productDTO.getDisscount());
                product.setQuantity(productDTO.getQuantity());
                product.setCategory(category.orElse(null));
                product.setTrademark(trademark.orElse(null));
                product.setIndustry(industry.orElse(null));
                product.setManufactureaddress(manufactureAddress.orElse(null));
                product.setSeller(seller.orElse(null));
                product.setIntroduct(productDTO.getIntroduce());
                product.setSold("0");
                product.setAdvertisement(false);
                product.setAttention("0");
                product.setStatus(Business.ONL);
                // Lưu sản phẩm vào cơ sở dữ liệu
                productRepository.save(product);
            }
        }
    }

    //Kiểm tra tồn tại của product
    public boolean isProductCodeExists(String productCode) {
        // Truy vấn cơ sở dữ liệu để kiểm tra xem có sản phẩm nào có mã productCode tồn tại hay không
        Optional<Product> product = Optional.ofNullable(productRepository.findByProductCode(productCode));
        return product.isPresent();
    }

    //R
    //Return list product
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //Lấy ra top 100 sản phẩm có lượng sold cao nhất và có trạng thái ONL
    public List<Product> getTopSoldProducts() {
        return productRepository.findTop100OnlineProductsBySold();
    }

    //Lấy ra sản phẩm mới thm từ 3 ngày gần đây
    public List<Product> getNewlyAddedProducts() {
        LocalDateTime startDate = LocalDateTime.now().minus(3, ChronoUnit.DAYS);
        return productRepository.findNewlyAddedProducts(startDate);
    }

    public List<String> compareProducts(List<Long> productIds) {
        List<Product> products = productRepository.findAllById(productIds);

        List<String> comparisonResults = new ArrayList<>();
        for (Product product : products) {
            String comparisonResult = buildComparisonResult(product);
            comparisonResults.add(comparisonResult);
        }

        return comparisonResults;
    }
    private String buildComparisonResult(Product product) {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Product Name: ").append(product.getProductName()).append("\n");
        resultBuilder.append("Product Code: ").append(product.getProductCode()).append("\n");
        resultBuilder.append("Date: ").append(product.getDate()).append("\n");
        resultBuilder.append("Sold: ").append(product.getSold()).append("\n");
        resultBuilder.append("Price: ").append(product.getPrice()).append("\n");
        resultBuilder.append("Introduction: ").append(product.getIntroduct()).append("\n");
        resultBuilder.append("Discount: ").append(product.getDisscount()).append("\n");

        // Lấy tên của Seller từ sellerid
        String sellerName = product.getSeller().getShopName();
        resultBuilder.append("Seller: ").append(sellerName).append("\n");

        // Lấy tên của Trademark từ trademarkid
        String trademarkName = product.getTrademark().getTrademarkName();
        resultBuilder.append("Trademark: ").append(trademarkName).append("\n");

        // Lấy tên của Industry từ industryid
        String industryName = product.getIndustry().getIndustryName();
        resultBuilder.append("Industry: ").append(industryName).append("\n");

        // Lấy tên của ManufactureAddress từ manufactureaddressid
        String manufactureAddressName = product.getManufactureaddress().getName();
        resultBuilder.append("Manufacture Address: ").append(manufactureAddressName).append("\n");
        return resultBuilder.toString();
    }

    //Return product by id
    public Optional<Product> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product;
        }else{
            throw new NotFoundException("Product không tồn tại trong hệ thống!");
        }
    }

    //Tìm kiếm sản phẩm theo keyword
    //public List<Product> searchProducts(String keyword) {
        // Lưu từ khóa tìm kiếm vào bảng keyword
        //Keyword searchKeyword = new Keyword();
        //searchKeyword.setKeyword(keyword);
        //keywordRepository.save(searchKeyword);





        public List<Product> searchProducts(String keyword) {
            Keyword existingKeyword = keywordRepository.findByKeyword(keyword);
            if (existingKeyword != null) {
                existingKeyword.setCount(existingKeyword.getCount() + 1);
                keywordRepository.save(existingKeyword);
            } else {
                List<Keyword> similarKeywords = keywordRepository.findAll();
                boolean isNewKeyword = true;

                for (Keyword similarKeyword : similarKeywords) {
                    if (isKeywordSimilar(similarKeyword.getKeyword(), keyword)) {
                        similarKeyword.setCount(similarKeyword.getCount() + 1);
                        keywordRepository.save(similarKeyword);
                        isNewKeyword = false;
                    }
                }

                if (isNewKeyword) {
                    Keyword newKeyword = new Keyword();
                    newKeyword.setKeyword(keyword);
                    newKeyword.setCount(1L);
                    keywordRepository.save(newKeyword);
                }
            }

            // Thực hiện tìm kiếm sản phẩm
            return productRepository.findByProductNameContainingIgnoreCaseOrderByProductNameAsc(keyword);
        }

        private boolean isKeywordSimilar(String keyword1, String keyword2) {
            // Logic để kiểm tra sự giống nhau và mở rộng từ khóa
            // Trong ví dụ này, sử dụng toLowerCase() và contains() để kiểm tra
            return keyword1.toLowerCase().contains(keyword2.toLowerCase());
        }




//    public List<Product> getFlassaleProducts(double discountPercentage) {
//        String discountPercentageStr = String.valueOf(discountPercentage);
//        return productRepository.findAllByDisscountGreaterThan(discountPercentageStr);
//    }


    //D
    //update product
    @Override
    public void updateProduct(Long productId, ProductDTO productDTO){
        // Kiểm tra xem sản phẩm có tồn tại dựa trên ID hay không
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (!existingProduct.isPresent()) {
            //throw new RuntimeException("Không tìm thấy sản phẩm với ID: " + productId);
        }
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
        // Kiểm tra xem trademarkId có hợp lệ hay không
        Long sellerId = productDTO.getSellerId();
        Optional<Seller> seller = sellerService.getSellerById(sellerId);
        if (seller == null) {
            // Trả về lỗi nếu trademarkId không hợp lệ
        }
        if(existingProduct.isPresent() && category.isPresent() && trademark.isPresent() && industry.isPresent() && manufactureAddress.isPresent() && seller.isPresent()) {
            // Cập nhật thông tin của sản phẩm
            Product product = existingProduct.get();
            product.setProductName(productDTO.getProductName());
            product.setProductCode(productDTO.getProductCode());
            product.setPrice(productDTO.getPrice());
            product.setDisscount(productDTO.getDisscount());
            product.setQuantity(productDTO.getQuantity());
            product.setCategory(category.orElse(null));
            product.setIndustry(industry.orElse(null));
            product.setTrademark(trademark.orElse(null));
            product.setManufactureaddress(manufactureAddress.orElse(null));
            product.setIntroduct(productDTO.getIntroduce());
            // Lưu sản phẩm đã cập nhật vào cơ sở dữ liệu
            productRepository.save(product);
        }
    }
    //Update business product by id
    public void updateProductByIdWithStatus(Long productId, Business status){
        // Kiểm tra xem sản phẩm có tồn tại dựa trên ID hay không
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (!existingProduct.isPresent()) {
            //throw new RuntimeException("Không tìm thấy sản phẩm với ID: " + productId);
        }
        if(existingProduct.isPresent()){
            Product product = existingProduct.get();
            product.setStatus(status);
            // Lưu sản phẩm đã cập nhật vào cơ sở dữ liệu
            productRepository.save(product);
        }
    }

    //Update status product by id
    public void updateProductByIdWithAdvertisement(Long productId, Boolean advertisement){
        // Kiểm tra xem sản phẩm có tồn tại dựa trên ID hay không
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (!existingProduct.isPresent()) {
            //throw new RuntimeException("Không tìm thấy sản phẩm với ID: " + productId);
        }
        if(existingProduct.isPresent()){
            Product product = existingProduct.get();
            product.setAdvertisement(advertisement);
            // Lưu sản phẩm đã cập nhật vào cơ sở dữ liệu
            productRepository.save(product);
        }
    }

    //D
    //Delete product by id
    public void deleteProductById(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            productRepository.deleteById(productId);
        }
    }
}
