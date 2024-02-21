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
import com.app.dao.CompanyDao;
import com.app.dao.CompanyRequirementDao;
import com.app.dto.ApiResponse;
import com.app.dto.CompanyRequirementsDTO;
import com.app.entities.Company;
import com.app.entities.CompanyRequirement;

@Service
@Transactional
public class CompanyRequirementServiceImpl implements CompanyRequirementService {
	
	@Autowired
	private CompanyRequirementDao compReqDao;
	@Autowired
	private CompanyDao compDao;
	@Autowired
	private ModelMapper mapper;
	
	public List<CompanyRequirementsDTO> getAllRequirementsFromCompany(Long compId){
		List<CompanyRequirement> compReqList=compReqDao.findByCompId(compId);
		return compReqList.stream()
				.map(compReq -> mapper.map(compReq, CompanyRequirementsDTO.class))
				.collect(Collectors.toList());	
	}

	@Override
	public ApiResponse deleteCompRequirements(Long compReqId) {
		CompanyRequirement compReq=compReqDao.findById(compReqId)
				.orElseThrow(()->new ResourceNotFoundException("Invalid Id"));
		compReqDao.deleteById(compReqId);
		return new ApiResponse("Company Requirement details with ID"+compReq.getId()+"deleted....");
	}

	@Override
	public CompanyRequirementsDTO addnewCompanyRequirements(CompanyRequirementsDTO dto) {
		Company comp=compDao.findById(dto.getCompId())
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Company Id!!"));
		CompanyRequirement compReq=mapper.map(dto, CompanyRequirement.class);
		comp.addRequirement(compReq);
		CompanyRequirement savedComReq=compReqDao.save(compReq);
		System.out.println("Company Requirement id"+compReq.getId()+ " " +savedComReq.getId());
		return mapper.map(savedComReq, CompanyRequirementsDTO.class);
	}

	@Override
	public CompanyRequirementsDTO updateCompanyRequirements(Long compReqId, CompanyRequirementsDTO dto) {
		CompanyRequirement compReq=compReqDao.findById(compReqId)
				.orElseThrow(()->new ResourceNotFoundException("Invalid Id,Company Requirement not found"));
		Company comp=compDao.findById(dto.getCompId())
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Company Id "));
		mapper.map(dto, compReq);
		System.out.println("After mapping"+compReq);
		comp.addRequirement(compReq);
		dto.setId(compReqId);
		return dto;
	}

	@Override
	public CompanyRequirementsDTO getCompRequirementsDetails(Long compId, Long compReqId) {
		CompanyRequirement compReq=compReqDao.findById(compReqId).orElseThrow(()-> new ResourceNotFoundException("Invalid Company RequirementsId"));
		if(compReq.getComp().getId()!=compId) {
			throw new ResourceNotFoundException("Company Id does not match !!");
		}
		return mapper.map(compReq, CompanyRequirementsDTO.class);
	}

	@Override
	public List<CompanyRequirementsDTO> getAllCompanyRequirements(int pageNumber, int pageSize) {
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		
		List<CompanyRequirement> compReqList=compReqDao.findAll(pageable).getContent();
		return compReqList.stream()
				.map(compReq->mapper.map(compReq, CompanyRequirementsDTO.class))
				.collect(Collectors.toList());
	}
	
	
	
}
