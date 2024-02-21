package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.app.entities.JobProvider;

public interface JobProviderDao extends JpaRepository<JobProvider, Long> {

	@Query("select j from JobProvider j left join fetch j.joblist where j.id=?1")
	JobProvider getJobProviderAndJobDetails(Long JobProviderId);
	
	Optional<JobProvider> findByJobProviderEmailAndPassword(String em,String pass);
}
