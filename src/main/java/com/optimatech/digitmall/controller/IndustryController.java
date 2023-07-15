package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.model.dto.IndustryDTO;
import com.optimatech.digitmall.model.entity.Industry;
import com.optimatech.digitmall.services.IndustryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/industries")
public class IndustryController {
    @Autowired
    private final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @Operation(
            summary = "client gửi request GetMethod để hiển thị danh sách các Danh Mục ",
            description = " "
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Industry không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    @GetMapping("")
    public ResponseEntity<?> getAllIndustry(){
    List<Industry> industries = industryService.getAllIndustries();
    return  ResponseEntity.ok(industries);
    }

    @Operation(
            summary = "client gửi request PostMethod để thêm Danh Mục ",
            description = "Seller có thể thêm sản phẩm vào sop của mình với from frontend thiết kế "
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Industry không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createIndustry(@RequestBody IndustryDTO industryDTO){
        industryService.createIndustry(industryDTO);
        return ResponseEntity.ok("Thành công!");
    }

    @Operation(
            summary = "client gửi request GetMethod để hiển thị  Danh Mục ",
            description = " "
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "Industry không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })
    @GetMapping("/{industryId}")
    public ResponseEntity<?> getIndustryById(@PathVariable Long industryId){
        return  ResponseEntity.ok(industryService.getIndustryById(industryId));
    }
}
