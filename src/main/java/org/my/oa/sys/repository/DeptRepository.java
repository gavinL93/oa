package org.my.oa.sys.repository;

import java.util.HashMap;
import java.util.List;

import org.my.oa.sys.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DeptRepository extends JpaRepository<Dept, Long> {

	@Query("select new Map(t.id, t.name) from Dept t")
	public List<HashMap<String, Object>> findDepts();
}
