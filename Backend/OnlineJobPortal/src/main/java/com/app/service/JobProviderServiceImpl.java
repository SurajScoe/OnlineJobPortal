package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.JobProviderDao;
import com.app.dto.ApiResponse;
import com.app.dto.JobProviderDto;
import com.app.dto.JobProviderJobDTO;
import com.app.entities.Address;
import com.app.entities.JobProvider;

@Service
@Transactional
public class JobProviderServiceImpl implements JobProviderService {

	@Autowired
	private JobProviderDao jobProviderDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<JobProviderDto> getAllJobProviders() {
		
		return jobProviderDao.findAll()
				.stream()
				.map(jobProvider->mapper.map(jobProvider, JobProviderDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public JobProviderDto getJobProviderDetails(Long jobProviderId) {
		
		return mapper.map(jobProviderDao.findById(jobProviderId)
				.orElseThrow(()->new ResourceNotFoundException("Invalid JobProviderId")), 
				JobProviderDto.class);
	}

	@Override
	public JobProviderJobDTO getJobProviderAndJobsDetails(Long jobProviderId) {
		JobProvider jobProvider= jobProviderDao.getJobProviderAndJobDetails(jobProviderId);
		return mapper.map(jobProvider, JobProviderJobDTO.class);
	}

	@Override
	public JobProviderDto addNewJobProvider(JobProviderDto dto) {
		Address add=new Address(dto.getAddress().getCity(), dto.getAddress().getDistrict(), dto.getAddress().getState(), dto.getAddress().getPinCode());
		JobProvider jobProvider=mapper.map(dto, JobProvider.class);
		jobProvider.setAddress(add);
		JobProvider persistentProvider=jobProviderDao.save(jobProvider);
		return mapper.map(persistentProvider, JobProviderDto.class);
	}

	@Override
	public JobProviderDto updateJobProvider(Long jobProviderId, JobProviderDto dto) {
		JobProvider jobProvider=jobProviderDao.findById(jobProviderId)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid JobProviderId!!!"));
		jobProvider.setJobProviderName(dto.getJobProviderName());
		jobProvider.setJobProviderEmail(dto.getJobProviderEmail());
		jobProvider.setPassword(dto.getPassword());
		Address add=new Address(dto.getAddress().getCity(), dto.getAddress().getDistrict(), dto.getAddress().getState(), dto.getAddress().getPinCode());
		jobProvider.setAddress(add);
		jobProviderDao.save(jobProvider);
		return mapper.map(jobProvider, JobProviderDto.class);
	}

	@Override
	public ApiResponse deleteJobProvider(Long jobProviderId) {
		JobProvider jobProvider=jobProviderDao.findById(jobProviderId)
				.orElseThrow(()->new ResourceNotFoundException("Invalid JobProviderId!!"));
		jobProviderDao.deleteById(jobProviderId);
		return new ApiResponse("Job Provider details with Id"+jobProvider.getId()+" deleted..");
	}

	
	
	
}
