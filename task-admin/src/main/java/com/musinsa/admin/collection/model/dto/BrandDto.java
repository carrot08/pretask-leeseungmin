package com.musinsa.admin.collection.model.dto;

import com.musinsa.core.collection.model.Brand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class BrandDto {

    @Setter
    @Getter
    public static class BrandCreateRequest {
        @NotBlank(message = "name must not be blank")
        private String name;

        public Brand toEntity() {
            return new Brand(name);
        }
    }

    @Setter
    @Getter
    public static class BrandUpdateRequest {
        @NotNull(message = "brandId not null")
        private Long brandId;
        @NotBlank(message = "name must not be blank")
        private String name;
    }

    @Setter
    @Getter
    public static class BrandDeleteRequest {
        @NotNull(message = "brandId not null")
        private Long brandId;
    }

}
