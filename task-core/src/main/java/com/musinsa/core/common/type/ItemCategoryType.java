package com.musinsa.core.common.type;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ItemCategoryType {

    TOP(1, "상의" ),
    OUTER(2, "아우터" ),
    PANTS(3, "바지" ),
    SNEAKERS(4, "스니커즈"),
    BAG(5, "가방"),
    CAP(6, "모자"),
    SOCKS(7, "양말"),
    ACCESSORY(8, "액세서리")
    ;
    private final Integer code;
    private final String categoryName;

    private static final Map<Integer, ItemCategoryType> CATEGORY_CODE = initCategoryCode();
    private static final Map<String, ItemCategoryType> CATEGORY_NAME = initCategoryName();

    ItemCategoryType(Integer code, String name) {
        this.code = code;
        this.categoryName = name;
    }

    private static Map<Integer, ItemCategoryType> initCategoryCode() {
        Map<Integer, ItemCategoryType> map = new HashMap<>();
        for (ItemCategoryType type : values()) {
            map.put(type.code, type);
        }
        return map;
    }

    private static Map<String, ItemCategoryType> initCategoryName() {
        Map<String, ItemCategoryType> map = new HashMap<>();
        for (ItemCategoryType type : values()) {
            map.put(type.categoryName, type);
        }
        return map;
    }

    public static ItemCategoryType getCategoryByCode(int code) {
        return Optional.ofNullable(CATEGORY_CODE.get(code)).orElse(TOP);
    }

    public static ItemCategoryType getCategoryByName(String name) {
        return Optional.ofNullable(CATEGORY_NAME.get(name)).orElse(TOP);
    }

    public static ItemCategoryType getCategory(String category) {

        return Optional.ofNullable(ItemCategoryType.valueOf(category)).orElse(TOP);
    }

    public String getName() {
        return this.categoryName;
    }

}
