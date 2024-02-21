package com.app.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
import com.app.dto.JobProviderDto;
import com.app.service.AddressService;
import com.app.service.JobProviderService;

@RestController
@RequestMapping("/jobProvider")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class JobProviderController {
	@Autowired
	private JobProviderService jobProvService;
	@Autowired
	private AddressService addService;
	
	@PostMapping
	public ResponseEntity<?> addNewJobProvider(@RequestBody @Valid JobProviderDto dto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(jobProvService.addNewJobProvider(dto));
	}
	
	@GetMapping("/{jobProviderId}")
	public ResponseEntity<?> getJobProviderDetails(@PathVariable Long jobProviderId){
		return ResponseEntity.ok(jobProvService.getJobProviderDetails(jobProviderId));
	}
	
	@GetMapping("/{jobProviderId}/jobs")
	public ResponseEntity<?> getJobProviderAndJobDetails(@PathVariable @Min(1) @Max(10) Long jobProviderId){
		return ResponseEntity.ok(jobProvService.getJobProviderAndJobsDetails(jobProviderId));
	}
	
	@GetMapping
	public ResponseEntity<?> getAllJobProviderDetails(){
		return ResponseEntity.ok(jobProvService.getAllJobProviders());
	}
	
	@PutMapping("/{jobProviderId}")
	public ResponseEntity<?> updateJobProvider(@PathVariable Long jobProviderId,@RequestBody @Valid JobProviderDto dto){
		return ResponseEntity.ok(jobProvService.updateJobProvider(jobProviderId, dto));
	}
	
	@DeleteMapping("/{jobProviderId}")
	public ResponseEntity<?> deleteJobProvider(@PathVariable Long jobProviderId){
		return ResponseEntity.ok(jobProvService.deleteJobProvider(jobProviderId));
	}
	
	@PostMapping("/{jobProviderId}")
	public ResponseEntity<?> assignJobProviderAddress(@PathVariable Long jobProviderId,@RequestBody @Valid AddressDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(addService.assignJopProviderAddress(jobProviderId, dto));
	}
	
	@PutMapping("/{jobProviderId}/address")
	public ResponseEntity<?> updateJobProviderAddress(@PathVariable Long jobProviderId,@RequestBody @Valid AddressDTO dto){
		return ResponseEntity.ok(addService.updateJobProviderAddress(jobProviderId, dto));
	}

}

