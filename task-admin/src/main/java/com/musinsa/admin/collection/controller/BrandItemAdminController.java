package com.musinsa.admin.collection.controller;

import com.musinsa.admin.collection.model.dto.BrandItemDto;
import com.musinsa.admin.collection.service.BrandItemService;
import com.musinsa.core.common.model.RestResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/api/v1/brandItem")
@RequiredArgsConstructor
@Controller
public class BrandItemAdminController {

    private final BrandItemService itemService;

    @PostMapping()
    public ResponseEntity createBrandItem(@RequestBody @Valid BrandItemDto.BrandItemCreateRequest request) {
        itemService.createBrandItem(request);
        return ResponseEntity.ok(RestResponse.success());
    }

    @PutMapping()
    public ResponseEntity updateBrandItem(@RequestBody @Valid BrandItemDto.BrandItemUpdateRequest request) {
        itemService.updateBrandItem(request);
        return ResponseEntity.ok(RestResponse.success());
    }

    @DeleteMapping()
    public ResponseEntity deleteBrandItem(@RequestBody @Valid BrandItemDto.BrandItemDeleteRequest request) {
        itemService.deleteBrandItem(request);
        return ResponseEntity.ok(RestResponse.success());
    }

}
