package nhom8.javabackend.hotel.hotel.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.CreateHotelImagesDto;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.CreateMultipleHotelImages;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.HotelImagesDto;
import nhom8.javabackend.hotel.hotel.dto.hotelimages.UpdateHotelImagesDto;
import nhom8.javabackend.hotel.hotel.entity.HotelImages;
import nhom8.javabackend.hotel.hotel.service.itf.HotelImagesService;

@RestController
@RequestMapping("/api/hotel-images")
public class HotelImagesController {

	private final String imagesDir = "hotel-images/";
	
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
		
		if(dto.getUrl().lastIndexOf(imagesDir) == -1) 
			return ResponseHandler.getResponse("Unvalid hotel image url", HttpStatus.BAD_REQUEST);
			
		dto.setThumbUrl(dto.getUrl().substring(dto.getUrl().lastIndexOf(imagesDir)));
		HotelImages hotelImages = service.createNewHotelImages(dto);
		
		return ResponseHandler.getResponse(hotelImages,HttpStatus.CREATED);
	}
	
	@PostMapping("/create-multiple-hotel-images")
	public Object createMultipleNewHotelImages(@Valid @RequestBody CreateMultipleHotelImages dto, BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		List<HotelImages> hotelImageList = new ArrayList<>();
		
		for(String url : dto.getListUrl()) {
			if(url.lastIndexOf(imagesDir) == -1) 
				return ResponseHandler.getResponse("Unvalid hotel image url", HttpStatus.BAD_REQUEST);
			
			String thumbUrl = url.substring(url.lastIndexOf(imagesDir));
			HotelImages hotelImages = service.createMultipleNewHotelImages(url, thumbUrl, dto.getHotelId());
			hotelImageList.add(hotelImages);
		}
		
		return ResponseHandler.getResponse(hotelImageList,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-hotel-images")
	public Object updateHotelImages(@Valid @RequestBody UpdateHotelImagesDto dto,BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		if(dto.getUrl().lastIndexOf(imagesDir) == -1) 
			return ResponseHandler.getResponse("Unvalid hotel image url", HttpStatus.BAD_REQUEST);
		
		dto.setThumbUrl(dto.getUrl().substring(dto.getUrl().lastIndexOf(imagesDir)));
		HotelImages hotelImages = service.updateHotelImages(dto);
		
		return ResponseHandler.getResponse(hotelImages,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{hotel-images-id}")
	public Object deleteHotelImages(@PathVariable("hotel-images-id")Long id) {
		try {
			service.deleteHotelCoverPic(id);
		
			return ResponseHandler.getResponse(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.getResponse(e,HttpStatus.BAD_REQUEST);
		}
	}
	
}
