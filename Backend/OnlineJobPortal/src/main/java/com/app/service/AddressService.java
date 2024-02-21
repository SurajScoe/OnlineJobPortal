package com.app.service;

import javax.validation.Valid;

import com.app.dto.AddressDTO;
import com.app.dto.ApiResponse;

public interface AddressService {

	ApiResponse assignCompanyAddress(Long compId,@Valid AddressDTO address);
	ApiResponse assignJopProviderAddress(Long jobProviderId,@Valid AddressDTO address);
	ApiResponse assignJobSeekerAddress(Long JobSeekerId,@Valid AddressDTO address);
	
	AddressDTO getAddressDetails(Long addressId);
	
	ApiResponse updateCompanyAddress(Long compId,@Valid AddressDTO address);
	ApiResponse updateJobProviderAddress(Long jobProviderId,@Valid AddressDTO address);
	ApiResponse updateJobSeekerAddress(Long JobSeekerId,@Valid AddressDTO address);
}
