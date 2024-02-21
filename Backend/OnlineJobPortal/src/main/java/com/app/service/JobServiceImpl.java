package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.JobDao;
import com.app.dao.JobProviderDao;
import com.app.dto.ApiResponse;
import com.app.dto.JobDTO;
import com.app.entities.Job;
import com.app.entities.JobProvider;

@Service
@Transactional
public class JobServiceImpl implements JobService {
	@Autowired
	private JobDao jobDao;
	@Autowired
	private JobProviderDao jobProviderDao;
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public List<JobDTO> getAllJobsByJobProvider(Long jobProviderId) {
		List<Job> jobList=jobDao.findByJobProvId(jobProviderId);
		return jobList.stream()
				.map(job->mapper.map(job, JobDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse deleteJobDetails(Long JobId) {
		Job job=jobDao.findById(JobId).orElseThrow(()-> new ResourceNotFoundException("Invalid Job id"));
		jobDao.deleteById(JobId);
		return new ApiResponse("Job details with id"+job.getId()+" "+"deleted..");
	}

	@Override
	public JobDTO getJobDetails(Long jobId) {
		Job job=jobDao.findById(jobId)
				.orElseThrow(()->new ResourceNotFoundException("Job ID does not match"));
		return mapper.map(job, JobDTO.class);
	}

	@Override
	public JobDTO addnewJob(JobDTO dto) {
		JobProvider jobProvider=jobProviderDao.findById(dto.getJobProvId())
				.orElseThrow(()->new ResourceNotFoundException("Invalid JobProviderId"));
		Job job=mapper.map(dto, Job.class);
		jobProvider.addJob(job);
		Job savedJob=jobDao.save(job);
		return mapper.map(savedJob, JobDTO.class);
	}

	@Override
	public JobDTO updateJobDetails(Long jobId,JobDTO dto) {
		Job job=jobDao.findById(jobId)
				.orElseThrow(()->new ResourceNotFoundException("Job ID does not match"));
		JobProvider jobProvider=jobProviderDao.findById(dto.getJobProvId())
				.orElseThrow(()->new ResourceNotFoundException("Invalid JobProviderId"));
		mapper.map(dto, job);
		jobProvider.addJob(job);
		dto.setId(jobId);
		return dto;
	}

	@Override
	public List<JobDTO> getAllJobs(int pageNumber, int pageSize) {
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		
		List<Job> jobList=jobDao.findAll(pageable).getContent();
		return jobList.stream()
				.map(job->mapper.map(job, JobDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public JobDTO getJobDetailsByJobProviderAndJobId(Long jobProviderId, Long jobId) {
		Job job=jobDao.findById(jobId).orElseThrow(()->new ResourceNotFoundException("Invaild JobId"));
		if(job.getJobProv().getId()!=jobProviderId) {
			throw new ResourceNotFoundException("Job Provider ID does not match");
		}
		return mapper.map(job, JobDTO.class);
	}

	
	
	
}
