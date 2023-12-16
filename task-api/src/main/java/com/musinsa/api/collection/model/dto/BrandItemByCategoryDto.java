package com.musinsa.api.collection.model.dto;

import com.musinsa.core.collection.model.BrandItem;
import com.musinsa.core.common.FormatUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BrandItemByCategoryDto {
    private List<BrandItemDto> itemList;
    private String totalPrice;

    public static BrandItemByCategoryDto create(List<BrandItem> list) {

        List<BrandItemDto> itemDtoList = new ArrayList<>();
        int total = 0;
        for(BrandItem item : list) {
            itemDtoList.add(BrandItemDto.create(item));
            total += item.getPrice();
        }

        BrandItemByCategoryDto dto = new BrandItemByCategoryDto();
        dto.itemList = itemDtoList;
        dto.totalPrice = FormatUtil.price(total);

        return dto;
    }
}
