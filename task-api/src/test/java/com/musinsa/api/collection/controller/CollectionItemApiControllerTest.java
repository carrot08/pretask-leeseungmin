package com.musinsa.api.collection.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.api.collection.model.dto.BrandItemByBrandDto;
import com.musinsa.api.collection.model.dto.BrandItemByCategoryDto;
import com.musinsa.api.collection.model.dto.BrandItemHighestLowesByCategoryDto;
import com.musinsa.api.collection.service.BrandItemService;
import com.musinsa.core.common.model.RestResponse;
import com.musinsa.core.common.type.ItemCategoryType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CollectionItemApiControllerTest {

    @Mock
    private BrandItemService itemService;
    @InjectMocks
    private CollectionItemApiController apiController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }

    @Test
    @DisplayName("카테고리별 최저가격 브랜드와 가격, 총액 BrandItemByCategoryDto")
    public void getLowestPriceItemByCategory() throws Exception {
        BrandItemByCategoryDto dto = new BrandItemByCategoryDto();
        given(itemService.getLowestPriceItemByCategory()).willReturn(dto);

        mockMvc.perform(get("/api/v1/items/lowest-in-category"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(RestResponse.success(dto))));
    }

    @Test
    @DisplayName("단일브랜드로 모든 카테고리 상품 구입시 최저가격 브랜드")
    public void getAllLowestPriceByBrand() throws Exception {
        BrandItemByBrandDto dto = new BrandItemByBrandDto();
        given(itemService.getAllLowestPriceByBrand()).willReturn(dto);

        mockMvc.perform(get("/api/v1/items/lowest-by-brand"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(RestResponse.success(dto))));
    }
    @Test
    @DisplayName("카테고리이름으로 최고,최저가 상품의 브랜드와 상품가격")
    public void getHighestLowestPriceByCategory() throws Exception {
        BrandItemHighestLowesByCategoryDto dto = new BrandItemHighestLowesByCategoryDto();
        String categoryType = ItemCategoryType.getCategoryByCode(1).name();
        given(itemService.getHighestAndLowestPriceByCategory(categoryType)).willReturn(dto);

        mockMvc.perform(get("/api/v1/items/lowest-hightest-by-category")
                        .param("categoryType", categoryType))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(RestResponse.success(dto))));
    }

}
