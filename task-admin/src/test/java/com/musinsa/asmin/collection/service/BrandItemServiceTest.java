package com.musinsa.asmin.collection.service;

import com.musinsa.admin.AdminMainApplication;
import com.musinsa.admin.collection.model.dto.BrandDto;
import com.musinsa.admin.collection.model.dto.BrandItemDto;
import com.musinsa.admin.collection.repository.BrandItemRepository;
import com.musinsa.admin.collection.repository.BrandRepository;
import com.musinsa.admin.collection.service.BrandItemService;
import com.musinsa.admin.collection.service.BrandService;
import com.musinsa.core.collection.model.Brand;
import com.musinsa.core.collection.model.BrandItem;
import com.musinsa.core.common.exception.CollectionRuntimeException;
import com.musinsa.core.common.type.ItemCategoryType;
import com.musinsa.core.common.type.ResultCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest(classes = AdminMainApplication.class)
public class BrandItemServiceTest {

    @Autowired
    private BrandItemRepository itemRepo;

    @Autowired
    private BrandItemService itemService;


    @Transactional
    @Test
    public void createBrandItemTest() {

        BrandItemDto.BrandItemCreateRequest request = new BrandItemDto.BrandItemCreateRequest();
        request.setBrandId(1L);
        request.setPrice(1200);
        request.setCategory(ItemCategoryType.TOP);

        int start = this.itemRepo.findAll().size();
        itemService.createBrandItem(request);
        int end = this.itemRepo.findAll().size();

        assertThat(start).isEqualTo(end - 1);
    }

    @Transactional
    @Test
    public void updateBrandItemTest() {
        List<BrandItem> list = itemRepo.findAll();
        BrandItem item = list.get(0);

        int chgPrice = 9000;
        BrandItemDto.BrandItemUpdateRequest request = new BrandItemDto.BrandItemUpdateRequest();
        request.setBrandItemId(item.getId());
        request.setPrice(chgPrice);
        request.setCategory(ItemCategoryType.ACCESSORY);

        itemService.updateBrandItem(request);

        BrandItem chgItem = itemRepo.getBrandItemById(item.getId());

        assertThat(chgItem.getCategoryName()).isEqualTo(ItemCategoryType.ACCESSORY.getName());
        assertThat(chgItem.getPrice()).isEqualTo(chgPrice);
    }

    @Transactional
    @Test
    public void deleteBrandItemTest() {
        List<BrandItem> list = itemRepo.findAll();
        BrandItem item = list.get(0);

        BrandItemDto.BrandItemDeleteRequest request = new BrandItemDto.BrandItemDeleteRequest();
        request.setBrandItemId(item.getId());

        int start = this.itemRepo.findAll().size();

        itemService.deleteBrandItem(request);
        int end = this.itemRepo.findAll().size();

        assertThat(start).isEqualTo(end + 1);
    }

}
