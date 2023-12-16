package com.musinsa.admin.collection.service;

import com.musinsa.admin.collection.model.dto.BrandItemDto;
import com.musinsa.admin.collection.repository.BrandItemRepository;
import com.musinsa.core.collection.model.BrandItem;
import com.musinsa.core.common.exception.CollectionRuntimeException;
import com.musinsa.admin.collection.model.dto.BrandDto;
import com.musinsa.admin.collection.repository.BrandRepository;
import com.musinsa.core.collection.model.Brand;
import com.musinsa.core.common.type.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BrandItemService {

    private final BrandRepository brandRepo;
    private final BrandItemRepository brandItemRepo;

    @Transactional
    public void createBrandItem(BrandItemDto.BrandItemCreateRequest request) {

        Brand brand = brandRepo.getBrandById(request.getBrandId());
        brandItemRepo.save(request.toEntity(brand));
    }

    @Transactional
    public void updateBrandItem(BrandItemDto.BrandItemUpdateRequest request) {
        BrandItem item = brandItemRepo.getBrandItemById(request.getBrandItemId());
        item.changeCategoryAndPrice(request.getCategory(), request.getPrice());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteBrandItem(BrandItemDto.BrandItemDeleteRequest request) {
        BrandItem item = brandItemRepo.getBrandItemById(request.getBrandItemId());
        brandItemRepo.delete(item);
    }
}
