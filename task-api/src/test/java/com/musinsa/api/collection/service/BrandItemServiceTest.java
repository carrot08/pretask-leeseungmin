package com.musinsa.api.collection.service;


import com.musinsa.api.collection.model.dto.BrandItemByBrandDto;
import com.musinsa.api.collection.model.dto.BrandItemByCategoryDto;
import com.musinsa.api.collection.model.dto.BrandItemDto;
import com.musinsa.api.collection.model.dto.BrandItemHighestLowesByCategoryDto;
import com.musinsa.api.collection.repository.BrandItemCustomRepository;
import com.musinsa.core.collection.model.Brand;
import com.musinsa.core.collection.model.BrandItem;
import com.musinsa.core.common.FormatUtil;
import com.musinsa.core.common.type.ItemCategoryType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrandItemServiceTest {

    @Mock
    private BrandItemCustomRepository itemRepo;

    @InjectMocks
    private BrandItemService itemService;

    private BrandItem ITEM_TOP_HIGHT;
    private BrandItem ITEM_TOP_LOW;
    private BrandItem ITEM_OUTER_LOW;

    @BeforeEach
    public void initData() {
        Brand brand_a = new Brand("A");
        Brand brand_b = new Brand("B");

        BrandItem t1 = new BrandItem(brand_a, ItemCategoryType.TOP, 12000);
        BrandItem t2 = new BrandItem(brand_b, ItemCategoryType.TOP, 10000);

        BrandItem o1 = new BrandItem(brand_a, ItemCategoryType.OUTER, 25000);
        BrandItem o2 = new BrandItem(brand_b, ItemCategoryType.OUTER, 20000);

        ITEM_TOP_HIGHT = t1;
        ITEM_TOP_LOW = t2;
        ITEM_OUTER_LOW = o2;
    }

    @Test
    @DisplayName("카테고리별 최저가격 브랜드와 가격, 총액")
    public void getLowestPriceItemByCategory() {
        List<BrandItem>   itemList = Arrays.asList(ITEM_TOP_LOW, ITEM_OUTER_LOW);


        when(itemRepo.findAllByCategoryOrderByPrice(true)).thenReturn(itemList);

        BrandItemByCategoryDto resultDto =  itemService.getLowestPriceItemByCategory();

        List<BrandItemDto> itemDtoList = itemList.stream().map(BrandItemDto::create).toList();
        String total = FormatUtil.price(itemList.stream().mapToInt(BrandItem::getPrice).sum());

        assertThat(itemDtoList).isEqualTo(resultDto.getItemList());
        assertThat(total).isEqualTo(resultDto.getTotalPrice());

    }

    @Test
    @DisplayName("단일브랜드로 모든 카테고리 상품 구입시 최저가격 브랜드")
    public void getAllLowestPriceByBrand() {
        List<BrandItem>   itemList = Arrays.asList(ITEM_TOP_LOW, ITEM_OUTER_LOW);


        when(itemRepo.findAllByBrandOrderByPriceAsc()).thenReturn(itemList);

        BrandItemByBrandDto resultDto =  itemService.getAllLowestPriceByBrand();

        String brandName = ITEM_TOP_LOW.getBrandName();
        List<BrandItemDto> itemDtoList = itemList.stream().map(BrandItemDto::createExceptBrand).toList();
        String total = FormatUtil.price(itemList.stream().mapToInt(BrandItem::getPrice).sum());

        assertThat(itemDtoList).isEqualTo(resultDto.getCategoryList());
        assertThat(total).isEqualTo(resultDto.getTotalPrice());
        assertThat(brandName).isEqualTo(resultDto.getBrandName());
    }

    @Test
    @DisplayName("카테고리이름으로 최고,최저가 상품의 브랜드와 상품가격")
    public void getHighestAndLowestPriceByCategory() {
        ItemCategoryType category = ItemCategoryType.TOP;

        when(itemRepo.findByCategoryOrderByPrice(category, true)).thenReturn(ITEM_TOP_LOW);
        when(itemRepo.findByCategoryOrderByPrice(category, false)).thenReturn(ITEM_TOP_HIGHT);

        BrandItemHighestLowesByCategoryDto resultDto =  itemService.getHighestAndLowestPriceByCategory(category.name());


        assertThat(category.name()).isEqualTo(ItemCategoryType.getCategoryByName(resultDto.getCategoryName()).name());

        assertThat(BrandItemDto.createExceptCategory(ITEM_TOP_LOW)).isEqualTo(resultDto.getLowestItem());
        assertThat(BrandItemDto.createExceptCategory(ITEM_TOP_HIGHT)).isEqualTo(resultDto.getHighestItem());
    }

}
