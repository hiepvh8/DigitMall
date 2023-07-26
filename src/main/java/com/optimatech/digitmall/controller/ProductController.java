package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.Enum.Business;
import com.optimatech.digitmall.model.dto.ProductDTO;
import com.optimatech.digitmall.model.entity.*;
import com.optimatech.digitmall.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products", description = "Các chức năng của products")
@RestController
@CrossOrigin("http://localhost:5500")
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    private final QualityService qualityService;

    public ProductController(ProductService productService, QualityService qualityService) {
        this.productService = productService;
        this.qualityService = qualityService;
    }

//    @Operation(
//            summary = "client gửi request GetMethod để trả về danh sách các sản phẩm",
//            description = ""
//    )
//
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK"),
//            @ApiResponse(responseCode = "400",description = "bad request"),
//            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
//            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
//    })
    //Return list product
//    @GetMapping("")
//    public ResponseEntity<?> gettAllProduct(){
//        List<Product> products = productService.getAllProducts();
//        return ResponseEntity.ok(products);
//    }

    @Operation(
            summary = "client gửi request GetMethod để trả về Sản phẩm theo id để xem thông tin sản phẩm",
            description = ""
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Return product by id
    @GetMapping("/{id}")
    public ResponseEntity<? > getProductById(@PathVariable Long  id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Operation(
            summary = "client gửi request GetMethod để trả về danh sách top 100 Sản phẩm có lượt bán cao nhất trong hệ thống",
            description = "Dùng để hiện thị danh sách sản phẩm có lượt bán cao nhất "
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })

    @GetMapping("/topSold")
    public ResponseEntity<?> getTopSoldProducts(){
        List<Product> products = productService.getTopSoldProducts();
        return ResponseEntity.ok(products);
    }

    @Operation(
            summary = "client gửi request GetMethod để trả về danh sách Sản phẩm vừa được thêm vào shop trong 3 ngày gần nhất trong hệ thống",
            description = "Dùng để hiện thị danh sách sản phẩm có lượt bán cao nhất "
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Trả về sản phẩm vừa được thêm 3 ngày gần nhất
    @GetMapping("/new")
    public ResponseEntity<?> getAllProductsLast3Days(){
        List<Product> products = productService.getNewlyAddedProducts();
        return ResponseEntity.ok(products);
    }

    @Operation(
            summary = "client gửi request PostMethod để trả về danh sách Sản phẩm vừa được thêm vào để so sánh",
            description = " Json để test là [\n" +
                    "            16,\n" +
                    "            17,\n" +
                    "            18\n" +
                    "            ] nếu lỗi thay id khác có trong db"
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Trả về những sản phẩm đem ra so sánh

    @PostMapping("/compare")
    public ResponseEntity<List<String>> compareProducts(@RequestBody List<Long> productIds) {
        List<String> comparisonResults = productService.compareProducts(productIds);
        return ResponseEntity.ok(comparisonResults);
    }

    @Operation(
            summary = "client gửi request GetMethod để tìm kiêm sản phẩm theo keyword truyền vào ",
            description = ""
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Tìm kiếm sản phẩm theo keyword
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("keyword") String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

    @Operation(
            summary = "getMethod trả về trung bình sao đánh giá của sản phẩm",
            description = ""
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    @GetMapping("/{productId}/star")
    public ResponseEntity<?> getStarProduct(@PathVariable Long productId) {
        Double star = qualityService.calculateAverageQualityWithProduct(productId);
        return ResponseEntity.ok(star);
    }
//    @GetMapping("/flassale")
//    public ResponseEntity<List<Product>> getFlassaleProducts(@RequestParam(required = false, defaultValue = "0.3") double disscountPercentage) {
//        List<Product> products = productService.getFlassaleProducts(disscountPercentage);
//        return ResponseEntity.ok(products);
//    }

    @Operation(
            summary = "client gửi request PostMethod để thêm sản phẩm ",
            description = "Seller có thể thêm sản phẩm vào sop của mình với from frontend thiết kế "
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })

    //register product
    @PostMapping("/create")
    public ResponseEntity<? > createProduct(@RequestBody ProductDTO productDTO){
        productService.createProduct(productDTO);
        return new ResponseEntity<>("Thêm thành công!", HttpStatus.CREATED);
    }

    @Operation(
            summary = "client gửi request PostMethod để Đánh giá sao cho nhà bán hàng",
            description = ""
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Quantity không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Cập nhật đánh giá cho sản phẩm
    @PostMapping("/{productId}/qualities")
    public ResponseEntity<?> createQuality(@PathVariable Long productId, @RequestBody Double star) {
        qualityService.createQuatityByProduct(productId, star);
        return new ResponseEntity<>("Thêm thành công!", HttpStatus.CREATED);
    }

    @Operation(
            summary = " Client gủi PutMethod yêu cầu chỉnh sửa sản phẩm ",
            description = "Seller có thể chỉnh sửa sản phẩm theo id của mình, seller sẽ có danh sách các sản phẩm trong shope của mình, mỗi sản phẩm có phần edit với from frontend thiết kế."
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })

    //Update product by id
    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        try {
            productService.updateProduct(productId, productDTO);
            return ResponseEntity.ok("Sản phẩm đã được cập nhật thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }

    @Operation(
            summary = " Client gủi PutMethod yêu cầu cập nhật trạng thái Quảng cáo sản phẩm ",
            description = "Seller có thể câp nhật trạng thái sản phẩm ONL, OFF "
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Update product by id with Advertisement
    @PutMapping("/update/advertisement/{productId}")
    public ResponseEntity<String> updateProductByIdWithAdvertisement(@PathVariable Long productId, @RequestBody Boolean advertisement) {
        try {
            productService.updateProductByIdWithAdvertisement(productId, advertisement);
            return ResponseEntity.ok("Sản phẩm đã được set trạng thái ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }

    @Operation(
            summary = " Client gủi PutMethod yêu cầu cập nhật trạng thái ONL,OFF sản phẩm đang kinh doanh hoặc đã hoãn kih doanh ",
            description = "Seller có thể câp nhật trạng thái sản phẩm ONL, OFF "
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Update product by id with Status
    @PutMapping("/update/status/{productId}")
    public ResponseEntity<String> updateProductByIdWithStatus(@PathVariable Long productId, @RequestBody Business status) {
        try {
            productService.updateProductByIdWithStatus(productId, status);
            return ResponseEntity.ok("Sản phẩm đã được set trạng thái ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }

//    @Operation(
//            summary = " Client gủi DeleteMethod yêu cầu xóa sản phẩm ",
//            description = "Xóa product truyền vào id khi ấn vào sản phẩm lưu ý xóa sản phẩm của seller tương ứng"
//    )
//
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK"),
//            @ApiResponse(responseCode = "400",description = "bad request"),
//            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
//            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
//    })

    @Operation(
            summary = " Client gủi DleteMethod yêu cầu xóa sản phẩm ",
            description = " "
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Delete product by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
        return ResponseEntity.ok("Sản phẩm đã xóa thành công");
    }
}
