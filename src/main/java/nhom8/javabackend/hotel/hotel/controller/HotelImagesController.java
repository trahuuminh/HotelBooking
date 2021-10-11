package nhom8.javabackend.hotel.hotel.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.CreateHotelImagesDto;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.HotelImagesDto;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.HotelImagesUploadDto;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.UpdateHotelImagesDto;
import nhom8.javabackend.hotel.hotel.entity.HotelImages;
import nhom8.javabackend.hotel.hotel.service.itf.HotelImagesService;

@RestController
@RequestMapping("/api/hotel-images")
public class HotelImagesController {

	private final String uploadDir="/src/main/resources/static/hotel-images/";
	
	private HotelImagesService service;
	
	public HotelImagesController(HotelImagesService hotelImagesService) {
		service=hotelImagesService;
	}
	
	@GetMapping("/find-all-hotel-images")
	public Object findAllHotelImages() {
		List<HotelImagesDto>hotelImagesList=service.findAllHotelImagesDto();
		
		return ResponseHandler.getResponse(hotelImagesList,HttpStatus.OK);
	}
	
	@PostMapping("/create-hotel-images")
	public Object createNewHotelImages(@Valid @RequestBody CreateHotelImagesDto dto, BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		HotelImages hotelImages=service.createNewHotelImages(dto);
		
		return ResponseHandler.getResponse(hotelImages,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-hotel-images")
	public Object updateHotelImages(@Valid @RequestBody UpdateHotelImagesDto dto,BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		HotelImages hotelImages=service.updateHotelImages(dto);
		
		return ResponseHandler.getResponse(hotelImages,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{hotel-images-id}")
	public Object deleteHotelImages(@PathVariable("hotel-images-id")Long id) {
		service.deleteHotelImages(id);
		
		return ResponseHandler.getResponse(HttpStatus.OK);
	}
	
	@PostMapping("/upload")
	public Object uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			
			String userDirectory=Paths.get("").toAbsolutePath().toString();
			
			Path folderPath = Paths.get(userDirectory + uploadDir);
			
			if(!Files.exists(folderPath)) {
				Files.createDirectories(folderPath);
			}
			
			Path path = Paths.get(userDirectory + uploadDir + fileName);
			
			Files.write(path, file.getBytes());
			HotelImagesUploadDto dto=new HotelImagesUploadDto();
			dto.setUrl(uploadDir + fileName);
			dto.setName(fileName);
			
			return ResponseHandler.getResponse(dto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
		}
	}
}
