package com.app.controller;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.AddressDTO;
import com.app.dto.JobSeekerDTO;
import com.app.dto.SigninRequest;
import com.app.service.AddressService;
import com.app.service.ImageHandlingService;
import com.app.service.JobSeekerService;

@RestController
@RequestMapping("/jobSeeker")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class JobSeekerController {

	@Autowired
	private JobSeekerService jobSeekerService;
	@Autowired
	private AddressService addService;
	@Autowired 
	private ImageHandlingService imageService;
	
	@PostMapping
	public ResponseEntity<?> addNewJobSeeker(@RequestBody @Valid JobSeekerDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(jobSeekerService.addnewJobSeeker(dto));
	}
	
	@GetMapping("/{jobSeekerId}")
	public ResponseEntity<?> getJobSeekerDetails(@PathVariable Long jobSeekerId){
		return ResponseEntity.ok(jobSeekerService.getJobSeekerDetails(jobSeekerId));
	}
	
	@PutMapping("/{jobSeekerId}")
	public ResponseEntity<?> updateJobSeeker(@PathVariable Long jobSeekerId,@RequestBody @Valid JobSeekerDTO dto){
		return ResponseEntity.ok(jobSeekerService.updateJobSeeker(jobSeekerId, dto));
	}
	
	@DeleteMapping("/{jobSeekerId}")
	public ResponseEntity<?> deleteJobSeeker(@PathVariable Long jobSeekerId){
		return ResponseEntity.ok(jobSeekerService.deleteJobSeeker(jobSeekerId));
	}
	
	@GetMapping
	public ResponseEntity<?> getAllJobSeekers(){
		return ResponseEntity.ok(jobSeekerService.getAllJobSeekers());
	}
	
	@PostMapping("/{jobSeekerId}")
	public ResponseEntity<?> assignJobSeekerAddress(@PathVariable Long jobSeekerId,@RequestBody @Valid AddressDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(addService.assignJobSeekerAddress(jobSeekerId, dto));
	}
	
	@PutMapping("/{jobSeekerId}/address")
	public ResponseEntity<?> updateJobSeekerAddress(@PathVariable Long jobSeekerId,@RequestBody @Valid AddressDTO dto){
		return ResponseEntity.ok(addService.updateJobSeekerAddress(jobSeekerId, dto));
	}
	
	@PostMapping(value = "images/{jobSeekerId}" ,consumes ={MediaType.MULTIPART_FORM_DATA_VALUE,IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE})
	public ResponseEntity<?> uploadImage(@PathVariable Long jobSeekerId, @RequestParam MultipartFile image)
			throws IOException {
		System.out.println("in upload image " + jobSeekerId);
		return ResponseEntity.status(HttpStatus.CREATED).body(imageService.uploadImage(jobSeekerId, image));
	}
	
	@GetMapping(value = "/images/{jobSeekerId}", produces = { IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
	public ResponseEntity<?> downloadImage(@PathVariable long jobSeekerId) throws IOException {
		System.out.println("in download image " + jobSeekerId);
		return ResponseEntity.ok(imageService.serveImage(jobSeekerId));
	}
	
	@PostMapping(value="/jobSeeker_images" , consumes ={MediaType.MULTIPART_FORM_DATA_VALUE,IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE})
	public ResponseEntity<?> uploadJobSeekerAndImage(@RequestPart MultipartFile image,@RequestPart JobSeekerDTO dto) throws IOException{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(jobSeekerService.addNewaddnewJobSeekerWithImage(dto, image));
	}
	
	@GetMapping("/jobSeeker/signIn")
	public ResponseEntity<?> jobSeekerSignIn(@RequestParam("email") @Email String email, @RequestParam("password") @Length(min = 3, max = 20) String password){
	    // Create a SigninRequest object if necessary
	    SigninRequest req = new SigninRequest(email, password);
	    return ResponseEntity.ok(jobSeekerService.jobSeekerSignIn(req));
	}
}
