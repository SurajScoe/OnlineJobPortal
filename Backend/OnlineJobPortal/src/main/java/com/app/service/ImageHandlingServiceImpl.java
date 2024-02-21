package com.app.service;

import static org.apache.commons.io.FileUtils.readFileToByteArray;
import static org.apache.commons.io.FileUtils.writeByteArrayToFile;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exception.ApiException;
import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.JobSeekerDao;
import com.app.dto.ApiResponse;
import com.app.entities.JobSeeker;

@Service
@Transactional
public class ImageHandlingServiceImpl implements ImageHandlingService {
	// injecting value of the field read from applicatoin.properties file
	@Value("${file.upload.location}") // field level DI , <property name n value />
	// ${file.upload.location} SpEL :Spring expr language
	private String uploadFolder;

	@Autowired
	private JobSeekerDao jobSeekerDao;

	@PostConstruct
	public void init() throws IOException {
		// chk if folder exists --yes --continue
		File folder = new File(uploadFolder);
		if (folder.exists()) {
			System.out.println("folder exists alrdy !");
		} else {
			// no --create a folder
			folder.mkdir();
			System.out.println("created a folder !");
		}
	}

	@Override
	public ApiResponse uploadImage(Long jobSeekerId, MultipartFile image) throws IOException {
		// get emp from emp id
		JobSeeker jobSeeker = jobSeekerDao.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("Invalid emp ID!!!!"));
		// emp found --> PERSISTENT
		// store the image on server side folder
		String path = uploadFolder.concat(image.getOriginalFilename());
		System.out.println(path);
		// Use FileUtils method : writeByte[] --> File
		writeByteArrayToFile(new File(path), image.getBytes());
		// set image path
		jobSeeker.setImagePath(path);
		// OR to store the img directly in DB as a BLOB
		// emp.setImage(image.getBytes());
		return new ApiResponse("Image file uploaded successfully for emp id " + jobSeeker);
	}

	@Override
	public void uploadImage(JobSeeker jobSeeker, MultipartFile image) throws IOException {
		// store the image on server side folder
		String path = uploadFolder.concat(image.getOriginalFilename());
		System.out.println(path);
		// Use FileUtils method : writeByte[] --> File
		writeByteArrayToFile(new File(path), image.getBytes());
		// set image path
		jobSeeker.setImagePath(path);
		// OR to store the img directly in DB as a BLOB
		// emp.setImage(image.getBytes());
		System.out.println("Image file uploaded successfully for emp " + jobSeeker.getFirstName());
	}

	@Override
	public byte[] serveImage(Long jobSeekerId) throws IOException {
		// get emp by id
		JobSeeker emp = jobSeekerDao.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("Invalid emp ID!!!!"));
		// emp found --> PERSISTENT
		String path = emp.getImagePath();
		if (path != null) {
			// path ---> File --> byte[]
			return readFileToByteArray(new File(path));
			// OR from DB : return emp.getImage();
		} else
			throw new ApiException("Image not yet assigned !!!!");

	}

}
