package nhom8.javabackend.hotel.s3.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StorageService {

	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Autowired
	private AmazonS3Client s3Client;
	
	public String uploadFile(MultipartFile file, String saveDir) {
		File fileObj = convertMultiPartFileToFile(file);
		String suffixImageName = saveDir.endsWith("/") ? saveDir : saveDir + "/";
		suffixImageName = suffixImageName.startsWith("/") ? suffixImageName.substring(1) : suffixImageName;
		String fileName = suffixImageName + System.currentTimeMillis()+"-"+file.getOriginalFilename().replaceAll("/", "_");
		
		s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj).withCannedAcl(CannedAccessControlList.PublicRead));
		fileObj.delete();
		
		return s3Client.getResourceUrl(bucketName, fileName);
	}
	
	public boolean deleteFile(String fileName) {
		try {
			s3Client.deleteObject(bucketName, fileName);
			return true;
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			log.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}

}
