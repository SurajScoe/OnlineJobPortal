package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.CompanyRequirement;

public interface CompanyRequirementDao extends JpaRepository<CompanyRequirement, Long> {
	
	
	//Finder: JPQL select c from CompanyRequirement c where c.comp.id=:id
	List<CompanyRequirement> findByCompId(Long companyId);
}
