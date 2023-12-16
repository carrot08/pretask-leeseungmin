package com.musinsa.api.collection.controller;

import com.musinsa.api.collection.service.BrandItemService;
import com.musinsa.core.common.model.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
@RestController
public class CollectionItemApiController {

    private final BrandItemService itemService;

    @GetMapping("/lowest-in-category")
    public ResponseEntity getLowPriceByCategory() {

        return ResponseEntity.ok(RestResponse.success(itemService.getLowestPriceItemByCategory()));
    }

    @GetMapping("/lowest-by-brand")
    public ResponseEntity getAllLowPriceByBrand() {

        return ResponseEntity.ok(RestResponse.success(itemService.getAllLowestPriceByBrand()));
    }

    @GetMapping("/lowest-hightest-by-category")
    public ResponseEntity getHighestLowestPriceByCategory(@RequestParam("categoryType") String categoryType) {

        return ResponseEntity.ok(RestResponse.success(itemService.getHighestAndLowestPriceByCategory(categoryType)));
    }
}
