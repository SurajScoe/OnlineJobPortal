package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CompanyRequirementsDTO;
import com.app.service.CompanyRequirementService;

@RestController
@RequestMapping("/reqirements")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyRequirementController {
	
	@Autowired
	private CompanyRequirementService compReqService;
	
	@PostMapping
	public ResponseEntity<?> addNewCompanyrequirement(@RequestBody @Valid CompanyRequirementsDTO dto){
		return ResponseEntity.ok(compReqService.addnewCompanyRequirements(dto));
	}
	
	@GetMapping("/company/{compId}")
	public ResponseEntity<?> getCompanyRequirement(@PathVariable Long compId){
		return ResponseEntity.ok(compReqService.getAllRequirementsFromCompany(compId));
	}
	
	@GetMapping("/company/{compId}/{compReqId}")
	public ResponseEntity<?> getCompanyRequirementDetails(@PathVariable Long compId,@PathVariable Long compReqId){
		return ResponseEntity.ok(compReqService.getCompRequirementsDetails(compId, compReqId));
	}
	
	@PutMapping("/{compReqId}")
	public ResponseEntity<?> updateCompanyRequirement(@PathVariable Long compReqId,@RequestBody CompanyRequirementsDTO dto){
		return ResponseEntity.ok(compReqService.updateCompanyRequirements(compReqId, dto));
	}
	
	@DeleteMapping("/{compReqId}")
	public ResponseEntity<?> deleteCompanyRequirement(@PathVariable Long compReqId){
		return ResponseEntity.ok(compReqService.deleteCompRequirements(compReqId));
	}
	
	@GetMapping
	public ResponseEntity<?> getAllRequirementsPaginated(@RequestParam(defaultValue = "0",required = false)int pageNumber,
			@RequestParam(defaultValue = "3",required = false)int pageSize){
		
		List<CompanyRequirementsDTO> list=compReqService.getAllCompanyRequirements(pageNumber, pageSize);
		if(list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(list);
	}
}
