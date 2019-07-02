package org.my.oa.sys.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.my.common.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "OA_ROLE")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 4740271295896021748L;

	// REMARK VARCHAR(500) 备注
	@Column(name = "REMARK", length = 500)
	private String remark;

	// MODIFIER VARCHAR(50) 修改人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "MODIFIER", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_ROLE_MODIFIER"))
	private User modifier;

	// CREATER VARCHAR(50) 修改人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "CREATER", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_ROLE_CREATER"))
	private User creater;

	// 角色与用户存在多对多的关联关系
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinTable(name = "OA_USER_ROLE", joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_ROLE_ID")), inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_ID")))
	private Set<User> users = new HashSet<>();
}
