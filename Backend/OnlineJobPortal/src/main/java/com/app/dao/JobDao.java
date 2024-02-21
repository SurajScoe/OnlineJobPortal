package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Job;

public interface JobDao extends JpaRepository<Job, Long> {
	List<Job> findByJobProvId(Long jobProvId);
}
