package nhom8.javabackend.hotel.s3.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

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
		String fileName = suffixImageName + System.currentTimeMillis()+"-"+file.getOriginalFilename().replaceAll("/", "_");
		
		s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj).withCannedAcl(CannedAccessControlList.PublicRead));
		fileObj.delete();
		
		return s3Client.getResourceUrl(bucketName, fileName);
	}
	
	public List<String> uploadListOfFiles(MultipartFile[] files, String saveDir) {
		List<String> fileUrlList = new LinkedList<>();
		String suffixImageName = saveDir.endsWith("/") ? saveDir : saveDir + "/";
		suffixImageName = suffixImageName.startsWith("/") ? suffixImageName.substring(1) : suffixImageName;

		// Upload file list to S3
		try {
			for(MultipartFile file: files) {
				File fileObj = convertMultiPartFileToFile(file);
				String fileName = suffixImageName + System.currentTimeMillis()+"-"+file.getOriginalFilename().replaceAll("/", "_");
				s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj).withCannedAcl(CannedAccessControlList.PublicRead));
				fileObj.delete();
				
				fileUrlList.add(s3Client.getResourceUrl(bucketName, fileName));
			}
			
		} catch (AmazonServiceException e) {
			log.error("uploadListOfFiles: ",e);
		}
		
		return fileUrlList;
	}
	
	public byte[] downloadFile(String fileName) {
		S3Object s3Object = s3Client.getObject(bucketName, fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		try {
			byte[] content = IOUtils.toByteArray(inputStream);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
