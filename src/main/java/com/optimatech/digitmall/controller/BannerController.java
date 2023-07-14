package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.model.entity.Banner;
import com.optimatech.digitmall.services.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/banners")
public class BannerController {
    @Autowired
    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    //R
    @Operation(
            summary = "client gửi request GetMethod để trả về danh sách các banner",
            description = ""
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Banner không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Return list banner
    @GetMapping
    public ResponseEntity<?> getAllBanners(){
        List<Banner> banners = bannerService.getAllBanners();
        return ResponseEntity.ok(banners);
    }

}
