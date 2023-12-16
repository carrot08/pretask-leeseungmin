package com.musinsa.api.collection.model.dto;

import com.musinsa.core.collection.model.BrandItem;
import com.musinsa.core.common.type.ItemCategoryType;
import lombok.Getter;

@Getter
public class BrandItemHighestLowesByCategoryDto {
    private String categoryName;
    private BrandItemDto lowestItem;
    private BrandItemDto highestItem;

    public static BrandItemHighestLowesByCategoryDto create(ItemCategoryType category, BrandItem lowestItem, BrandItem highestItem) {

        BrandItemHighestLowesByCategoryDto dto = new BrandItemHighestLowesByCategoryDto();
        dto.categoryName = category.getName();
        dto.lowestItem = BrandItemDto.createExceptCategory(lowestItem);
        dto.highestItem = BrandItemDto.createExceptCategory(highestItem);

        return dto;
    }
}
