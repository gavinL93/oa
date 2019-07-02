package org.my.oa.sys.repository;

import java.util.List;
import java.util.Map;

import org.my.oa.sys.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long> {
	
	@Query("select new Map(j.id , j.code, j.name) from Job j")
	public List<Map<String, Object>> findJobs();
}
