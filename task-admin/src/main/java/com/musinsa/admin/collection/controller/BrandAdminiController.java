package com.musinsa.admin.collection.controller;

import com.musinsa.admin.collection.model.dto.BrandDto;
import com.musinsa.admin.collection.service.BrandService;
import com.musinsa.core.common.model.RestResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/api/v1/brand")
@RequiredArgsConstructor
@Controller
public class BrandAdminiController {

    private final BrandService brandService;

    @PostMapping()
    public ResponseEntity createBrand(@RequestBody @Valid BrandDto.BrandCreateRequest request) {
        brandService.createBrand(request);
        return ResponseEntity.ok(RestResponse.success());
    }

    @PutMapping()
    public ResponseEntity updateBrand(@RequestBody @Valid BrandDto.BrandUpdateRequest request) {
        brandService.updateBrand(request);
        return ResponseEntity.ok(RestResponse.success());
    }

    @DeleteMapping()
    public ResponseEntity deleteBrand(@RequestBody @Valid BrandDto.BrandDeleteRequest request) {
        brandService.deleteBrand(request);
        return ResponseEntity.ok(RestResponse.success());
    }
}
