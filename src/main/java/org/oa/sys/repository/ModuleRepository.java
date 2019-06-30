package org.oa.sys.repository;

import java.util.List;

import org.oa.sys.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModuleRepository extends JpaRepository<Module, Long>, JpaSpecificationExecutor<Module> {

	@Modifying
	@Query("delete Module m where m.code like ?1")
	public void setCode(String code) ;
    
	@Query("select m from Module m where m.code like :parentCode and length(m.code) = :sonCodeLen")
	public List<Module> findModules(@Param("parentCode")String parentCode, @Param("sonCodeLen")int sonCodeLen);
	
	@Query("select Max(m.code) from Module m where m.code like :parentCode and  length(m.code) = :sonCodeLen ")
	public String findUniqueEntity(@Param("parentCode")String parentCode, @Param("sonCodeLen")int sonCodeLen);
}
