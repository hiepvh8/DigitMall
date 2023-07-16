package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.model.dto.IndustryDTO;
import com.optimatech.digitmall.model.dto.TrademarkDTO;
import com.optimatech.digitmall.model.entity.Trademark;
import com.optimatech.digitmall.services.TrademarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5500")
@RequestMapping("/trademarks")
public class TrademarkController {
    @Autowired
    private final TrademarkService trademarkService;

    public TrademarkController(TrademarkService trademarkService) {
        this.trademarkService = trademarkService;
    }

    @Operation(
            summary = "client gửi request GetMethod để hiển thị danh sách các Thương hiệu ",
            description = ""
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Trademark không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    @GetMapping("")
    public ResponseEntity<?> getAllTrademark(){
        List<Trademark> trademarks = trademarkService.getAllTrademark();
        return ResponseEntity.ok(trademarks);
    }

    @Operation(
            summary = "client gửi request PostMethod để thêm mới 1 Thương hiệu ",
            description = ""
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Trademark không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createTrademark(@RequestBody TrademarkDTO trademarkDTO){
        trademarkService.createTrademark(trademarkDTO);
        return ResponseEntity.ok("Thành công!");
    }

    @Operation(
            summary = "client gửi request GetMethod để thêm mới 1 Thương hiệu ",
            description = ""
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Trademark không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    //Trả về Thương hiệu cụ thể
    @GetMapping("/{trademarkId}")
    public ResponseEntity<?> getIndustryById(@PathVariable Long trademarkId){
        return  ResponseEntity.ok(trademarkService.getTrademarkById(trademarkId));
    }
}
