package com.musinsa.core.collection.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@SQLDelete(sql = "UPDATE brand SET deleted_datetime = NOW() WHERE id = ?")
@Where(clause = "deleted_datetime is null")
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<BrandItem> itemList = new ArrayList<>();

    @Embedded
    private TimeAudit time;

    public Brand(String name) {
        this.name = name;
        this.time = new TimeAudit();
    }

    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }

    public void changeName(String name) {
        this.name = name;
    }

}
