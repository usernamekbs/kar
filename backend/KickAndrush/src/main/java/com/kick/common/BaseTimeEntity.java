package com.kick.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) 
public abstract class BaseTimeEntity{

    @CreatedDate
    @Column(updatable = false)
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));

    @LastModifiedDate
    private String modifiedDate =LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));
}


