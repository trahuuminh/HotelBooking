package nhom8.javabackend.hotel.s3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.s3.service.StorageService;

@RestController
@RequestMapping("/file")
@CrossOrigin(methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST}, origins = "*", allowedHeaders = "*")
public class StorageController {

	@Autowired
	private StorageService service;
	
	@PostMapping("/upload/{saveDir}")
	public Object uploadFile(@RequestParam(value = "file") MultipartFile file, @PathVariable("saveDir") String saveDir) {
		return ResponseHandler.getResponse(service.uploadFile(file,saveDir), HttpStatus.OK);
	}
	
	@PostMapping("/upload-multiple-files/{saveDir}")
	public Object uploadFile(@RequestParam(value = "files") MultipartFile[] files, @PathVariable("saveDir") String saveDir) {
		return ResponseHandler.getResponse(service.uploadListOfFiles(files ,saveDir), HttpStatus.OK);
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
		byte[] data = service.downloadFile(fileName);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity
				.ok()
				.contentLength(data.length)
				.header("Content-type", "application/octet-stream")
				.header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
				.body(resource);
	}
	
	@GetMapping("/delete/{fileName}")
	public Object deleteFile(@PathVariable String fileName) {
		return ResponseHandler.getResponse(service.deleteFile(fileName), HttpStatus.OK);
	}
}
