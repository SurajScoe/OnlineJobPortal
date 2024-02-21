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

import com.app.dto.JobDTO;
import com.app.service.JobService;

@RestController
@RequestMapping("/jobs")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

	@Autowired
	private JobService jobService;
	
	@PostMapping
	public ResponseEntity<?> addNewJob(@RequestBody @Valid JobDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(jobService.addnewJob(dto));
	}
	
	@GetMapping("/jobProvider/{jobProviderId}")
	public ResponseEntity<?> getJobDetailsByJobProvider(@PathVariable Long jobProviderId){
		return ResponseEntity.ok(jobService.getAllJobsByJobProvider(jobProviderId));
	}
	
	@GetMapping("/jobProvider/{jobProviderId}/{jobId}")
	public ResponseEntity<?> getJobDetailsByJobProviderAndJobId(@PathVariable Long jobProviderId,@PathVariable Long jobId){
		return ResponseEntity.ok(jobService.getJobDetailsByJobProviderAndJobId(jobProviderId, jobId));
	}
	
	@GetMapping("/{jobId}")
	public ResponseEntity<?> getJobDetails(@PathVariable Long jobId){
		return ResponseEntity.ok(jobService.getJobDetails(jobId));
	}
	
	@PutMapping("/{jobId}")
	public ResponseEntity<?> updateJob(@PathVariable Long jobId,@RequestBody JobDTO dto){
		return ResponseEntity.ok(jobService.updateJobDetails(jobId, dto));
	}
	
	@DeleteMapping("/{jobId}")
	public ResponseEntity<?> deleteJob(@PathVariable Long jobId){
		return ResponseEntity.ok(jobService.deleteJobDetails(jobId));
	}
	
	@GetMapping
	public ResponseEntity<?> getAllJobsPaginated(@RequestParam(defaultValue = "0", required = false) int pageNumber,
			@RequestParam(defaultValue = "3", required = false) int pageSize){
		List<JobDTO> list=jobService.getAllJobs(pageNumber, pageSize);
		if(list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(list);
	}
}
