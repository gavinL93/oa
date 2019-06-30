package org.oa.sys.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.oa.common.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模块内的权限
 * 
 * @author LiangJiawen
 *
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "OA_POPEDOM")
public class Popedom extends BaseEntity {

	private static final long serialVersionUID = -3217924244773768143L;

	// 权限与模块存在多对一的关系
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Module.class)
	@JoinColumn(name = "MODULE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_POPEDOM_MODULE"))
	private Module module;

	// 权限与操作是多对一的关系
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Module.class)
	@JoinColumn(name = "OPERA_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_POPEDOM_OPERA"))
	private Module opera;

	// 权限与角色是多对一的关系
	// 权限与操作是多对一的关系
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class)
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_POPEDOM_ROLE"))
	private Role role;

	// MODIFIER VARCHAR(50) 修改人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "MODIFIER", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_POPEDOM_MODIFIER"))
	private User modifier;

	// CREATER VARCHAR(50) 修改人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "CREATER", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_POPEDOM_CREATER"))
	private User creater;
}
