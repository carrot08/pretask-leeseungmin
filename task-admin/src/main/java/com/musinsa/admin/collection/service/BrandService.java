package com.musinsa.admin.collection.service;

import com.musinsa.admin.collection.model.dto.BrandDto;
import com.musinsa.admin.collection.repository.BrandRepository;
import com.musinsa.core.common.exception.CollectionRuntimeException;
import com.musinsa.core.collection.model.Brand;
import com.musinsa.core.common.type.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BrandService {
    private final BrandRepository brandRepo;

    @Transactional
    public void createBrand(BrandDto.BrandCreateRequest request) {

        brandRepo.findByName(request.getName())
                .ifPresent( b-> {
                    throw new CollectionRuntimeException(ResultCode.BAD_REQUEST_DUPLICATE_BRAND_NAME);
                });

        brandRepo.save(request.toEntity());
    }

    @Transactional
    public void updateBrand(BrandDto.BrandUpdateRequest request) {
        Brand brand = brandRepo.getBrandById(request.getBrandId());
        brand.changeName(request.getName());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteBrand(BrandDto.BrandDeleteRequest request) {
        Brand brand = brandRepo.getBrandById(request.getBrandId());
        brandRepo.delete(brand);
    }

}
