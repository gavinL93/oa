package org.oa.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.oa.common.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "OA_MODULE", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }, name = "UQ_MODULE_CODE"))
public class Module extends BaseEntity {

	private static final long serialVersionUID = -9061932230116610720L;

	// CODE 代码
	@Column(name = "CODE", length = 100)
	private String code;
	
	// URL VARCHAR(50) 模块链接
	@Column(name = "URL", length = 50)
	private String url;

	// REMARK VARCHAR(500) 备注
	@Column(name = "REMARK", length = 500)
	private String remark;

	// MODIFIER VARCHAR(50) 修改人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "MODIFIER", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_MODULE_MODIFIER"))
	private User modifier;

	// CREATER VARCHAR(50) 修改人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "CREATER", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_MODULE_CREATER"))
	private User creater;

}
