package com.musinsa.api.collection.service;

import com.musinsa.api.collection.model.dto.BrandItemByBrandDto;
import com.musinsa.api.collection.model.dto.BrandItemByCategoryDto;
import com.musinsa.api.collection.model.dto.BrandItemHighestLowesByCategoryDto;
import com.musinsa.api.collection.repository.BrandItemCustomRepository;
import com.musinsa.core.common.type.ItemCategoryType;
import com.musinsa.core.collection.model.BrandItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandItemService {

    private final BrandItemCustomRepository itemRepo;

    @Transactional(readOnly = true)
    public BrandItemByCategoryDto getLowestPriceItemByCategory() {
        List<BrandItem> itemList = itemRepo.findAllByCategoryOrderByPrice(true);
        return BrandItemByCategoryDto.create(itemList);
    }

    @Transactional(readOnly = true)
    public BrandItemByBrandDto getAllLowestPriceByBrand() {
        return BrandItemByBrandDto.create(itemRepo.findAllByBrandOrderByPriceAsc());
    }

    @Transactional(readOnly = true)
    public BrandItemHighestLowesByCategoryDto getHighestAndLowestPriceByCategory(String categoryType) {
        ItemCategoryType category = ItemCategoryType.getCategory(categoryType);

        return BrandItemHighestLowesByCategoryDto.create(
                category,
                itemRepo.findByCategoryOrderByPrice(category, true),
                itemRepo.findByCategoryOrderByPrice(category, false)
        );

    }
}
