package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.entities.JobSeeker;

public interface ImageHandlingService {
	ApiResponse uploadImage(Long jobSeekerId, MultipartFile image) throws IOException;
	byte[] serveImage(Long jobSeekerId) throws IOException;
	//used for uploading img along with jobSeeker details
	void uploadImage(JobSeeker jobSeeker, MultipartFile image) throws IOException;
}
