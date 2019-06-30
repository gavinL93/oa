package org.oa.sys.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.oa.common.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "OA_USER", indexes = {
		@Index(columnList = "NAME", name = "IDX_USER_NAME") }, uniqueConstraints = @UniqueConstraint(columnNames = {
				"MOBILE" }, name = "UQ_USER_MOBILE"))
public class User extends BaseEntity {

	private static final long serialVersionUID = -6649598873548648860L;
	
	// 手机号
	@Column(name = "MOBILE")
	private String mobile;

	// 密码 md5加密
	@Column(name = "PASSWORD", length = 50)
	private String password;

	// 性别
	@Column(name = "SEX")
	private short sex;

	// 用户与部门存在多对一的关系 FK(OA_DEPT)
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Dept.class)
	@JoinColumn(name = "DEPT_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_DEPT"))
	private Dept dept;

	// 用户与职业存在多对一的关系 FK(OA_JOB)
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Job.class)
	@JoinColumn(name = "JOB_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_JOB"))
	private Job job;

	// 状态：0-新建 1-审核 2-审核不通过 3-冻结
	@Column(name = "STATUS")
	private short status;

	// MODIFIER VARCHAR(50) 修改人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "MODIFIER", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_MODIFIER"))
	private User modifier;

	// CREATER VARCHAR(50) 修改人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "CREATER", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_CREATER"))
	private User creater;

	// CHECKER VARCHAR(50) 审核人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "CHECKER", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_CHECKER"))
	private User checker;

	// CHECK_DATA 审核时间
	@Column(name = "CHECK_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkDate;

	// 用户与角色存在多对多的关系
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Role.class, mappedBy = "users")
	private Set<Role> roles = new HashSet<>();
}
