package com.musinsa.admin.collection.repository;

import com.musinsa.core.collection.model.BrandItem;
import com.musinsa.core.common.exception.CollectionRuntimeException;
import com.musinsa.core.common.type.ResultCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandItemRepository extends JpaRepository<BrandItem, Long> {
    default BrandItem getBrandItemById(Long brandItemId) {
        return findById(brandItemId)
                .orElseThrow(() -> new CollectionRuntimeException(ResultCode.NOT_FOUND_BRAND_ITEM));
    }
}
