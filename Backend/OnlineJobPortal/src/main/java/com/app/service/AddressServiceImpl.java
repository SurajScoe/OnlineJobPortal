package com.app.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.AddressDao;
import com.app.dao.CompanyDao;
import com.app.dao.JobProviderDao;
import com.app.dao.JobSeekerDao;
import com.app.dto.AddressDTO;
import com.app.dto.ApiResponse;
import com.app.entities.Address;
import com.app.entities.Company;
import com.app.entities.JobProvider;
import com.app.entities.JobSeeker;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressDao addDao;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CompanyDao compDao;
	@Autowired
	private JobProviderDao jobProviderDao;
	@Autowired
	private JobSeekerDao jobSeekerDao;
	
	@Override
	public ApiResponse assignCompanyAddress(Long compId,AddressDTO address) {
		Company comp=compDao.findById(compId)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Company Id"));
		Address add=mapper.map(address, Address.class);
		comp.setAddress(add);
		compDao.save(comp);
		return new ApiResponse("Assigned new Address to company "+comp.getCompanyName());
	}

	@Override
	public ApiResponse assignJopProviderAddress(Long jobProviderId, @Valid AddressDTO address) {
		JobProvider jobProvider=jobProviderDao.findById(jobProviderId)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Job ProviderId Id"));
		Address add=mapper.map(address, Address.class);
		jobProvider.setAddress(add);
		jobProviderDao.save(jobProvider);
		return new ApiResponse("Assigned new Address to JobProvider "+jobProvider.getJobProviderName());
	}

	@Override
	public ApiResponse assignJobSeekerAddress(Long JobSeekerId, @Valid AddressDTO address) {
		JobSeeker jobSeeker=jobSeekerDao.findById(JobSeekerId)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Job ProviderId Id"));
		Address add=mapper.map(address, Address.class);
		jobSeeker.setAddress(add);
		jobSeekerDao.save(jobSeeker);
		return new ApiResponse("Assigned new Address to JobSeeker "+jobSeeker.getFirstName());
	}

	@Override
	public AddressDTO getAddressDetails(Long addressId) {
		
		return mapper.map(addDao.findById(addressId)
				.orElseThrow(()->new ResourceNotFoundException("Invalid AddressId")), AddressDTO.class);
	}

	@Override
	public ApiResponse updateCompanyAddress(Long compId, @Valid AddressDTO address) {
		Company comp=compDao.findById(compId)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Company Id"));
		Address add=comp.getAddress();
		mapper.map(address, add);
		comp.setAddress(add);
		compDao.save(comp);
		return new ApiResponse("Updated address for  Company "+comp.getCompanyName());
	}

	@Override
	public ApiResponse updateJobProviderAddress(Long jobProviderId, @Valid AddressDTO address) {
		JobProvider jobProvider=jobProviderDao.findById(jobProviderId)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Job ProviderId Id"));
		Address add=jobProvider.getAddress();
		mapper.map(address, add);
		jobProvider.setAddress(add);
		jobProviderDao.save(jobProvider);
		return new ApiResponse("Updated address for  JobProvider "+jobProvider.getJobProviderName());
	}

	@Override
	public ApiResponse updateJobSeekerAddress(Long JobSeekerId, @Valid AddressDTO address) {
		JobSeeker jobSeeker=jobSeekerDao.findById(JobSeekerId)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Job ProviderId Id"));
		Address add=jobSeeker.getAddress();
		mapper.map(address, add);
		jobSeeker.setAddress(add);
		jobSeekerDao.save(jobSeeker);
		return new ApiResponse("Updated address for  JobSeeker "+jobSeeker.getFirstName());
	}
	
	
}
