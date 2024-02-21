package com.app.service;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.dto.JobSeekerDTO;
import com.app.dto.SigninRequest;

public interface JobSeekerService {
	
	JobSeekerDTO getJobSeekerDetails(Long jobseekerID);
	
	List<JobSeekerDTO> getAllJobSeekers();
	
	JobSeekerDTO addnewJobSeeker(JobSeekerDTO dto);
	
	JobSeekerDTO updateJobSeeker(Long jobSeekerID,JobSeekerDTO dto);
	
	ApiResponse deleteJobSeeker(Long jobseekerID);
	
	JobSeekerDTO addNewaddnewJobSeekerWithImage(JobSeekerDTO dto,MultipartFile Image) throws IOException;

	JobSeekerDTO jobSeekerSignIn(@Valid SigninRequest req);
}
