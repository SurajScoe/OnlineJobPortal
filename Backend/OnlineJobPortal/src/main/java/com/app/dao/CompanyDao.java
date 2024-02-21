package com.app.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Company;

public interface CompanyDao extends JpaRepository<Company, Long>{
	
	@Query("select c from Company c left join fetch c.compsreq where c.id=?1")
	Company getCompanyAndRequirements(Long compId);
	
	Optional<Company> findByCompanyEmailAndPassword(String em,String pass);
}
