package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.AddressDao;
import com.app.dao.CompanyDao;
import com.app.dao.CompanyRequirementDao;
import com.app.dto.ApiResponse;
import com.app.dto.CompanyDTO;
import com.app.dto.CompanyJobListingsDTO;
import com.app.entities.Address;
import com.app.entities.Company;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao compDao;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private CompanyRequirementDao compReqDao;
	
	@Override
	public List<CompanyDTO> getallCompanies() {
		System.out.println("In company service" );
		List<Company> companyList=compDao.findAll();
		return companyList.stream()
				.map(company->mapper.map(company, CompanyDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public CompanyDTO getCompanyDetails(Long compId) {
		return mapper.map(compDao.findById(compId)
				.orElseThrow(()->new ResourceNotFoundException("Invalid Company Id")),
				CompanyDTO.class);
	}

	@Override
	public CompanyJobListingsDTO getCompanyJobListings(Long compId) {
		Company company=compDao.getCompanyAndRequirements(compId);
		return mapper.map(company, CompanyJobListingsDTO.class);
	}

	@Override
	public CompanyDTO addNewCompany(CompanyDTO company) {
		Address add=new Address(company.getAddress().getCity(), company.getAddress().getDistrict(), company.getAddress().getState(), company.getAddress().getPinCode());
		addressDao.save(add);
		Company compEntity=mapper.map(company, Company.class);
		compEntity.setAddress(add);
		Company persistentComp=compDao.save(compEntity);
		return mapper.map(persistentComp, CompanyDTO.class);
	}

	@Override
	public CompanyDTO updateCompany(Long compId, CompanyDTO comp) {
		System.out.println("In company service" );
		Company company=compDao.findById(compId)
				.orElseThrow(()->new ResourceNotFoundException("Invalid CompanyID"));
		System.out.println("after comp" );
		company.setCompanyName(comp.getCompanyName());
		company.setCompanyEmail(comp.getCompanyEmail());
		Address add=new Address(comp.getAddress().getCity(), comp.getAddress().getDistrict(), comp.getAddress().getState(), comp.getAddress().getPinCode());
		System.out.println("after add" );
		company.setAddress(add);
		compDao.save(company);
		return mapper.map(company, CompanyDTO.class);
	}
	
	public ApiResponse deleteCompany(Long compId) {
		Company company=compDao.findById(compId).orElseThrow(()->new ResourceNotFoundException("Invalid Id"));
		compDao.deleteById(compId);
		return new ApiResponse("Company details with Id"+company.getId()+"deleted..");
	}

}
