package org.oa.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.oa.common.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "OA_USER", indexes = {
        @Index(columnList = "NAME", name = "IDX_USER_NAME") }, uniqueConstraints = @UniqueConstraint(columnNames = {
                "MOBILE" }))
public class User extends BaseEntity {

    private static final long serialVersionUID = -6649598873548648860L;

    // ID PK主键自增
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    // 密码 md5加密
    @Column(name = "PASSWORD", length = 50)
    private String password;

    // 姓名
    @Column(name = "NAME", length = 50)
    private String name;

    // 性别
    @Column(name = "SEX")
    private short sex;

    // 手机号
    @Column(name = "MOBILE")
    private String mobile;
}
