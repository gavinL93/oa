package org.my.oa.sys.repository;

import java.util.List;

import org.my.oa.sys.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModuleRepository extends JpaRepository<Module, Long>, JpaSpecificationExecutor<Module> {

	@Query("select m from Module m where m.code like :parentCode and length(m.code) = :sonCodeLen")
	public List<Module> findModules(@Param("parentCode")String parentCode, @Param("sonCodeLen")int sonCodeLen);
	
	@Query("select Max(m.code) from Module m where m.code like :parentCode and  length(m.code) = :sonCodeLen ")
	public String findUniqueEntity(@Param("parentCode")String parentCode, @Param("sonCodeLen")int sonCodeLen);

    public Module findByCode(String code);
}
