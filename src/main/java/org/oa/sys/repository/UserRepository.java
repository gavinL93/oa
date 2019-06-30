package org.oa.sys.repository;

import java.util.List;

import org.oa.sys.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

	public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
		@Query("select u.id from User u where u.id not in(select u.id from User u inner join u.roles r where r.id = ?1)")
		List<String> getRolesUsers(long id);
	
		@Query("select u.id from User u inner join u.roles r where r.id = ?1")
		List<String> findRoleUsers(long id);
	}
