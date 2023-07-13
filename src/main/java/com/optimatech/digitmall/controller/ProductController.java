package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.model.dto.ProductDTO;
import com.optimatech.digitmall.model.entity.*;
import com.optimatech.digitmall.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Products", description = "Các chức năng của products")
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "client gửi request GetMethod để trả về danh sách các sản phẩm",
            description = ""
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Return list product
    @GetMapping("")
    public ResponseEntity<?> gettAllProduct(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

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
    @PostMapping("/register")
    public ResponseEntity<? > createProduct(@RequestBody ProductDTO productDTO){
        productService.createProduct(productDTO);
        return new ResponseEntity<>("Thêm thành công!", HttpStatus.CREATED);
    }

    @Operation(
            summary = " Client gủi PutMethod yêu cầu chỉnh sửa sản phẩm ",
            description = "Seller có thể chỉnh sửa sản phẩm theo id của mình, seller sẽ có danh sách các sản phẩm trong shope của mình, mỗi sản phẩm có phần edit với from frontend thiết kế "
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
            summary = " Client gủi PutMethod yêu cầu cập nhật trạng thái sản phẩm ",
            description = "Seller có thể câp nhật trạng thái sản phẩm ONL, OFF "
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Product không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Update product by id with Business
    @PutMapping("/update/Business/{productId}")
    public ResponseEntity<String> updateProductByIdWithBusiness(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        try {
            productService.updateProductByIdWithBusiness(productId, productDTO);
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
