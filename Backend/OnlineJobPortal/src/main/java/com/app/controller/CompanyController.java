package com.app.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddressDTO;
import com.app.dto.CompanyDTO;
import com.app.service.AddressService;
import com.app.service.CompanyService;

@RestController
@RequestMapping("/company")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {
	
	@Autowired
	private CompanyService compService;
	@Autowired
	private AddressService addService;
	
	@GetMapping
	public ResponseEntity<?> getAllCompanyDetails(){
		System.out.println("In company controller" );
		return ResponseEntity.ok(compService.getallCompanies());
	}
	
	@PostMapping
	public ResponseEntity<?> addNewCompany(@RequestBody @Valid CompanyDTO company){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(compService.addNewCompany(company));
	}

	@GetMapping("/{compId}")
	public ResponseEntity<?> getCompanyDetails(@PathVariable Long compId){
		return ResponseEntity.ok(compService.getCompanyDetails(compId));
	}
	
	@GetMapping("/{compId}/requirements")
	public ResponseEntity<?> getCompAndCompRequirements(@PathVariable Long compId){
		return ResponseEntity.ok(compService.getCompanyJobListings(compId));
	}
	
	@PutMapping("/{compId}")
	public ResponseEntity<?> updateCompany(@PathVariable Long compId,@RequestBody @Valid CompanyDTO comp){
		System.out.println("In company controller" );
		return ResponseEntity.ok(compService.updateCompany(compId, comp));
	}
	
	@DeleteMapping("/{compId}")
	public ResponseEntity<?> deleteCompany(@PathVariable Long compId){
		return ResponseEntity.ok(compService.deleteCompany(compId));
	}
	@PostMapping("/{compId}")
	public ResponseEntity<?> assignCompanyAddress(@PathVariable Long compId,@RequestBody @Valid AddressDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(addService.assignCompanyAddress(compId, dto));
	}
	
	@PutMapping("/{compId}/address")
	public ResponseEntity<?> updateCompanyrAddress(@PathVariable Long compId,@RequestBody @Valid AddressDTO dto){
		return ResponseEntity.ok(addService.updateCompanyAddress(compId, dto));
	}
}
