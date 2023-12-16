package com.musinsa.core.collection.repository;

import com.musinsa.core.collection.model.BrandItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitBrandItemRepository extends JpaRepository<BrandItem, Long> {
}
