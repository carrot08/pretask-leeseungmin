package com.musinsa.api.collection.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.core.common.FormatUtil;
import com.musinsa.core.collection.model.BrandItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandItemDto {
    private String categoryName;
    private String brandName;
    @JsonProperty("price")
    private String strPrice;

    public static BrandItemDto create(BrandItem item) {
        BrandItemDto dto = new BrandItemDto();
        dto.categoryName = item.getCategoryName();
        dto.brandName = item.getBrandName();
        dto.strPrice = FormatUtil.price(item.getPrice());
        return dto;
    }

    public static BrandItemDto createExceptBrand(BrandItem item) {
        BrandItemDto dto = new BrandItemDto();
        dto.categoryName = item.getCategoryName();
        dto.strPrice = FormatUtil.price(item.getPrice());
        return dto;
    }

    public static BrandItemDto createExceptCategory(BrandItem item) {
        BrandItemDto dto = new BrandItemDto();
        dto.brandName = item.getBrandName();
        dto.strPrice = FormatUtil.price(item.getPrice());
        return dto;
    }

}
