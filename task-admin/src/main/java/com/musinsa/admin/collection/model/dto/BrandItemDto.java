package com.musinsa.admin.collection.model.dto;

import com.musinsa.core.collection.model.Brand;
import com.musinsa.core.collection.model.BrandItem;
import com.musinsa.core.common.type.ItemCategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;


public class BrandItemDto {

    @Setter
    @Getter
    public static class BrandItemCreateRequest {

        @NotNull(message = "category not null")
        private ItemCategoryType category;

        @PositiveOrZero(message = "price must be greater than 0.")
        private int price;

        @NotNull(message = "brandId not null")
        private Long brandId;

        public BrandItem toEntity(Brand brand) {
            return new BrandItem(brand, category, price);
        }
    }

    @Setter
    @Getter
    public static class BrandItemUpdateRequest {
        @NotNull(message = "brandItemId not null")
        private Long brandItemId;

        @NotNull(message = "category not null")
        private ItemCategoryType category;

        @PositiveOrZero(message = "price must be greater than 0.")
        private int price;
    }

    @Setter
    @Getter
    public static class BrandItemDeleteRequest {
        @NotNull(message = "brandItemId not null")
        private Long brandItemId;
    }

}
