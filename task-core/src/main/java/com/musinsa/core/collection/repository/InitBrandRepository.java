package com.musinsa.core.collection.repository;

import com.musinsa.core.collection.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitBrandRepository extends JpaRepository<Brand, Long> {
}
