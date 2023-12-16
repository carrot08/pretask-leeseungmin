package com.musinsa.core.collection.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Embeddable
public class TimeAudit {
    @CreatedDate
    @Column(name = "created_datetime", nullable = false, updatable = false)
    private LocalDateTime createdDate = java.time.LocalDateTime.now();
    @LastModifiedDate
    @Column(name = "updated_datetime", nullable = false)
    private LocalDateTime updatedDateTime = java.time.LocalDateTime.now();

    @Column(name = "deleted_datetime")
    private LocalDateTime deleteDateTime;
}
