package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.app.entities.JobSeeker;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Long> {

	
	Optional<JobSeeker> findByEmailAndPassword(String em,String pass);
}
