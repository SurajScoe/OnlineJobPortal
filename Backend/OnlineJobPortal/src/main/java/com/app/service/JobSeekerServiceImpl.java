package com.app.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exception.ApiException;
import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.AddressDao;
import com.app.dao.JobSeekerDao;
import com.app.dto.ApiResponse;
import com.app.dto.JobSeekerDTO;
import com.app.dto.SigninRequest;
import com.app.entities.Address;
import com.app.entities.JobSeeker;

@Service
@Transactional
public class JobSeekerServiceImpl implements JobSeekerService{

	@Autowired
	private JobSeekerDao jobSeekerDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ImageHandlingService imgHandlingService;
	
	@Override
	public JobSeekerDTO getJobSeekerDetails(Long jobseekerID) {
		JobSeeker jobSeeker=jobSeekerDao.findById(jobseekerID)
				.orElseThrow(()->new ResourceNotFoundException("Invalid JobseekerId"));
		return mapper.map(jobSeeker, JobSeekerDTO.class);
	}

	@Override
	public List<JobSeekerDTO> getAllJobSeekers() {
		return jobSeekerDao.findAll()
				.stream()
				.map(jobSeeker->mapper.map(jobSeeker, JobSeekerDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public JobSeekerDTO addnewJobSeeker(JobSeekerDTO dto) {
		Address add=new Address(dto.getAddress().getCity(), dto.getAddress().getDistrict(), dto.getAddress().getState(), dto.getAddress().getPinCode());
		addressDao.save(add);
		JobSeeker jobSeeker=mapper.map(dto, JobSeeker.class);
		jobSeeker.setAddress(add);
		JobSeeker persistentJobSeeker=jobSeekerDao.save(jobSeeker);
		return mapper.map(persistentJobSeeker, JobSeekerDTO.class);
	}

	@Override
	public JobSeekerDTO updateJobSeeker(Long jobSeekerID, JobSeekerDTO dto) {
		JobSeeker jobSeeker=jobSeekerDao.findById(jobSeekerID)
				.orElseThrow(()->new ResourceNotFoundException("Invalid Id,Jobseeker not found"));
		jobSeeker.setFirstName(dto.getFirstName());
		jobSeeker.setLastName(dto.getLastName());
		jobSeeker.setEmail(dto.getEmail());
		jobSeeker.setPassword(dto.getPassword());
		jobSeeker.setPhoneNo(dto.getPhoneNo());
		jobSeeker.setQualification(dto.getQualification());
		Address add=new Address(dto.getAddress().getCity(), dto.getAddress().getDistrict(), dto.getAddress().getState(), dto.getAddress().getPinCode());
		jobSeeker.setAddress(add);
		jobSeekerDao.save(jobSeeker);
		return mapper.map(jobSeeker, JobSeekerDTO.class);
	}

	@Override
	public ApiResponse deleteJobSeeker(Long jobseekerID) {
		JobSeeker jobSeeker=jobSeekerDao.findById(jobseekerID)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Id"));
		jobSeekerDao.deleteById(jobseekerID);
		return new ApiResponse("Jobseeker details with Id"+jobSeeker.getId()+"deleted..");
	}

	@Override
	public JobSeekerDTO addNewaddnewJobSeekerWithImage(JobSeekerDTO dto, MultipartFile image) throws IOException {
		JobSeeker jobSeekerEntity=mapper.map(dto, JobSeeker.class);
		imgHandlingService.uploadImage(jobSeekerEntity, image);
		JobSeeker savedjobSeeker=jobSeekerDao.save(jobSeekerEntity);
		return mapper.map(savedjobSeeker, JobSeekerDTO.class);
	}

	@Override
	public JobSeekerDTO jobSeekerSignIn(@Valid SigninRequest req) {
		JobSeeker jobSeeker=jobSeekerDao.findByEmailAndPassword(req.getEmail(), req.getPassword()).orElseThrow(()-> new ApiException("Invalid Credentials"));
		return mapper.map(jobSeeker, JobSeekerDTO.class);
	}

}
