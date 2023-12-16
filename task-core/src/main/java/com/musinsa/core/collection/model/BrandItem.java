package com.musinsa.core.collection.model;

import com.musinsa.core.common.type.ItemCategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brand_item")
@SQLDelete(sql = "UPDATE brand_item SET deleted_datetime = NOW() WHERE id = ?")
@Where(clause = "deleted_datetime is null")
public class BrandItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(name="category_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ItemCategoryType category;

    @ManyToOne(targetEntity = Brand.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    private TimeAudit time;


    public BrandItem(Brand brand, ItemCategoryType category, int price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.time = new TimeAudit();
    }
    public Long getId() {
        return this.id;
    }

    public int getPrice() {
        return this.price;
    }
    public String getCategoryName() {
       return this.category.getName();
    }
    public String getBrandName() {
        return this.brand.getName();
    }

    public void changeCategoryAndPrice(ItemCategoryType category, int price) {
        this.category = category;
        this.price = price;
    }
}
