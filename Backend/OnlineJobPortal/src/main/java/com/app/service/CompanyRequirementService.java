package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.CompanyRequirementsDTO;

public interface CompanyRequirementService {
	List<CompanyRequirementsDTO> getAllRequirementsFromCompany(Long compId);
	
	ApiResponse deleteCompRequirements(Long compReqId);
	
	CompanyRequirementsDTO addnewCompanyRequirements(CompanyRequirementsDTO dto);
	
	CompanyRequirementsDTO updateCompanyRequirements(Long compReqId,CompanyRequirementsDTO dto);
	
	CompanyRequirementsDTO getCompRequirementsDetails(Long compId,Long compReqId);
	
	List<CompanyRequirementsDTO> getAllCompanyRequirements(int pageNumber,int pageSize);
	
}
