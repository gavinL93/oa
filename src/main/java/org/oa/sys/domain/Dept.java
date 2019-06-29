package org.oa.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.oa.common.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "OA_DEPT")
public class Dept extends BaseEntity {

    private static final long serialVersionUID = 650869023393097574L;

    // ID bigint(20) 编号 PK主键自增
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    // NAME VARCHAR(50) 部门名称
    @Column(name = "NAME", length = 50)
    private String name;

    // REMARK VARCHAR(500) 备注
    @Column(name = "REMARK", length = 500)
    private String remark;

    // MODIFIER VARCHAR(50) 修改人
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "MODIFIER", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_DEPT_MODIFIER"))
    private User modifier;

}
