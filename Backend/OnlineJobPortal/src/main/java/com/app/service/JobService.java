package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.JobDTO;

public interface JobService {
	
	List<JobDTO> getAllJobsByJobProvider(Long jobProviderId);
	
	ApiResponse deleteJobDetails(Long JobId);
	
	JobDTO getJobDetails(Long jobId);
	
	JobDTO getJobDetailsByJobProviderAndJobId(Long jobProviderId,Long jobId);
	
	JobDTO addnewJob(JobDTO dto);
	
	JobDTO updateJobDetails(Long jobId,JobDTO dto);
	
	List<JobDTO> getAllJobs(int pageNumber,int pageSize);
}
