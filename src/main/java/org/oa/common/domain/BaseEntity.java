/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package org.oa.common.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity支持类
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "MODIFY_DATE")
    @LastModifiedDate
    private Date modifyDate;

    @Column(name = "CREATE_DATE")
    @CreatedDate
    private Date createDate;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
