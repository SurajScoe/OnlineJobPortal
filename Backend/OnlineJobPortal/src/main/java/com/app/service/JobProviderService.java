package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.JobProviderDto;
import com.app.dto.JobProviderJobDTO;

public interface JobProviderService {
	List<JobProviderDto> getAllJobProviders();
	JobProviderDto getJobProviderDetails(Long jobProviderId);
	JobProviderJobDTO getJobProviderAndJobsDetails(Long jobProviderId);
	JobProviderDto addNewJobProvider(JobProviderDto jobProvider);
	JobProviderDto updateJobProvider(Long jobProviderId,JobProviderDto jobProvider);
	ApiResponse deleteJobProvider(Long jobProviderId);
}
