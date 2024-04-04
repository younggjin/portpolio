package com.example.portfolio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
//시간 정보를 다룸
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class DateEntity {

    @CreationTimestamp//생성됐을 때 시간
    @Column(updatable = false)
    private LocalDateTime regdate;
}
