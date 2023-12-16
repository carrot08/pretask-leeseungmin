package com.musinsa.api.collection.model.dto;

import com.musinsa.core.collection.model.BrandItem;
import com.musinsa.core.common.FormatUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BrandItemByBrandDto {

    private String brandName;
    private List<BrandItemDto> categoryList;
    private String totalPrice;

    public static BrandItemByBrandDto create(List<BrandItem> itemList) {

        List<BrandItemDto> itemDtoList = new ArrayList<>();
        int total = 0;
        for(BrandItem item: itemList) {
            itemDtoList.add(BrandItemDto.createExceptBrand(item));
            total += item.getPrice();
        }

        BrandItemByBrandDto dto = new BrandItemByBrandDto();
        dto.brandName = itemList.get(0).getBrandName();
        dto.categoryList = itemDtoList;
        dto.totalPrice = FormatUtil.price(total);

        return dto;
    }
}
