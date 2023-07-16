package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.model.entity.Seller;
import com.optimatech.digitmall.services.QualityService;
import com.optimatech.digitmall.services.SellerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5500")
@RequestMapping("/sellers")
public class SellerController {
    @Autowired
    private final SellerService sellerService;

    private final QualityService qualityService;

    public SellerController(SellerService sellerService, QualityService qualityService) {
        this.sellerService = sellerService;
        this.qualityService = qualityService;
    }

    @Operation(
            summary = "client gửi request GetMethod để trả về Danh sách các nhà bán hàng",
            description = ""
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Seller không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Trả ve danh sách các nhà bán hàng
    @GetMapping("")
    public ResponseEntity<?> getAllSeller(){
        List<Seller> sellers = sellerService.getAllSellers();
        return ResponseEntity.ok(sellers);
    }

    @Operation(
            summary = "client gửi request GetMethod để trả thông tin nhà bán hàng",
            description = ""
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Seller không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Trả về thông tin seller
    @GetMapping("/{id}")
    public ResponseEntity<?> getSellerById(@PathVariable Long id){
        Optional<Seller> seller =sellerService.getSellerById(id);
        return  ResponseEntity.ok(seller);
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
    //Đánh giá cho nhà bán hàng
    @PostMapping("/{sellerId}/qualities")
    public ResponseEntity<? > createQuantityWithStarBySeller(@PathVariable Long sellerId, @RequestBody Double star){
        qualityService.createQuatityBySeller(sellerId,star);
        return new ResponseEntity<>("Thêm thành công!", HttpStatus.CREATED);
    }

    @Operation(
            summary = "GetMethod để trả về số sao đánh gi của nhà bán hàng",
            description = ""
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Quantity không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    @GetMapping("/{sellerId}/star")
    public ResponseEntity<?> getStarProduct(@PathVariable Long sellerId) {
        Double star = qualityService.calculateAverageQualityWithSeller(sellerId);
        return ResponseEntity.ok(star);
    }
}
