package com.musinsa.admin.collection.repository;

import com.musinsa.core.collection.model.Brand;
import com.musinsa.core.common.exception.CollectionRuntimeException;
import com.musinsa.core.common.type.ResultCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByName(String name);
    default Brand getBrandById(Long brandId) {
        return findById(brandId)
                .orElseThrow(() -> new CollectionRuntimeException(ResultCode.NOT_FOUND_BRAND));
    }
}
