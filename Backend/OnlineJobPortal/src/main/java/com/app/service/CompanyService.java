package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.CompanyDTO;
import com.app.dto.CompanyJobListingsDTO;

public interface CompanyService {
	
	List<CompanyDTO> getallCompanies();
	
	CompanyDTO getCompanyDetails(Long compId);
	
	CompanyJobListingsDTO getCompanyJobListings(Long compId);

	CompanyDTO addNewCompany(CompanyDTO comp);
	
	CompanyDTO updateCompany(Long compId,CompanyDTO company);
	
	ApiResponse deleteCompany(Long compId);
}
