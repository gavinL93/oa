package org.oa.sys.repository;

import java.util.HashMap;
import java.util.List;

import org.oa.sys.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DeptRepository extends JpaRepository<Dept, Long> {

	@Query("select new Map(p.id, p.name) from Dept t")
	public List<HashMap<String, Object>> findDepts();
}
