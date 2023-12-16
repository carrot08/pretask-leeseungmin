package com.musinsa.api.collection.repository;

import com.musinsa.core.collection.model.Brand;
import com.musinsa.core.common.type.ItemCategoryType;
import com.musinsa.core.collection.model.BrandItem;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.musinsa.core.collection.model.QBrand.brand;
import static com.musinsa.core.collection.model.QBrandItem.brandItem;

@RequiredArgsConstructor
@Repository
public class BrandItemCustomRepository {
    private final JPAQueryFactory queryFactory;

    public List<BrandItem> findAllByCategoryOrderByPrice(boolean ascending) {
        List<BrandItem> itemList = new ArrayList<>();
        for(ItemCategoryType category:ItemCategoryType.values()) {
            itemList.add(findByCategoryOrderByPrice(category, true));
        }
        return itemList;
    }
    public BrandItem findByCategoryOrderByPrice(ItemCategoryType category, boolean ascending) {

        return queryFactory
                .selectFrom(brandItem)
                .join(brandItem.brand, brand).fetchJoin()
                .where(
                        eqPrice(category, ascending),
                        eqCategory(category)
                )
                .orderBy(brandItem.brand.id.desc())
                .fetchFirst();
    }

    public List<BrandItem> findAllByBrandOrderByPriceAsc() {

        Brand lowBrand = queryFactory
                        .select(brandItem.brand)
                        .from(brandItem)
                        .groupBy(brandItem.brand.name)
                        .orderBy(brandItem.price.sum().asc())
                        .fetchFirst();


        return queryFactory
                .selectFrom(brandItem)
                .join(brandItem.brand, brand)
                .fetchJoin()
                .where(brandItem.brand.id.eq(lowBrand.getId()))
                .fetch();
    }

    private Predicate eqPrice(ItemCategoryType category, boolean ascending) {
        return brandItem.price.eq(
                JPAExpressions
                .select((ascending)? brandItem.price.min():brandItem.price.max())
                .from(brandItem)
                .where(brandItem.category.eq(category))
        );
    }
    private Predicate eqCategory(ItemCategoryType category) {
        return brandItem.category.eq(category);
    }
}
