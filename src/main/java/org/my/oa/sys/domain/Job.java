package org.my.oa.sys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.my.common.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "OA_JOB", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }, name = "UQ_JOB_CODE"))
public class Job extends BaseEntity {

	private static final long serialVersionUID = 6217008478303289267L;

	// CODE 代码
	@Column(name = "CODE", length = 100)
	private String code;

	// REMARK VARCHAR(500) 备注
	@Column(name = "REMARK", length = 500)
	private String remark;

	// MODIFIER VARCHAR(50) 修改人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "MODIFIER", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_JOB_MODIFIER"))
	private User modifier;

	// CREATER VARCHAR(50) 修改人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "CREATER", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_JOB_CREATER"))
	private User creater;

}
