package nhom8.javabackend.hotel.s3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.s3.fileDto;
import nhom8.javabackend.hotel.s3.service.StorageService;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST}, origins = "*", allowedHeaders = "*")
public class StorageController {

	private final String imagesDir = "hotel-images/";
	
	@Autowired
	private StorageService service;
	
	@PostMapping("/upload")
	public Object uploadFile(@RequestParam(value = "file") MultipartFile file) {
		return ResponseHandler.getResponse(service.uploadFile(file, imagesDir), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public Object deleteFile(@RequestBody fileDto dto) {
		if(service.deleteFile(dto.getFileName().indexOf(imagesDir) != 0 ? imagesDir + dto.getFileName() : dto.getFileName()))
			return ResponseHandler.getResponse(HttpStatus.OK);
		else return ResponseHandler.getResponse("Delete image failed. Please check your file name. File name must begin with 'hotel-images/...'", HttpStatus.BAD_REQUEST);
	}
}
