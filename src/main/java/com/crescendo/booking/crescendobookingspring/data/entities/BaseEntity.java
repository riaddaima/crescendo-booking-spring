package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue
    protected long id;

    @Column(name="created_at", updatable = false)
    @CreatedDate
    private long createdDate;

    @Column(name="modified_at")
    @LastModifiedDate
    private long modifiedDate;

    @Column(name="created_by", updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(name="modified_by")
    @LastModifiedBy
    private String modifiedBy;
}
