package org.pratice.donemile.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(value={AuditingEntityListener.class})
@MappedSuperclass
abstract class BaseEntity {
    @Column(name="createAt",  updatable = false)
    @CreatedDate
    private LocalDateTime createAt;

    @Column(name="modifiedAt")
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
