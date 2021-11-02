package nhom8.javabackend.hotel.hotel.controller;

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
import nhom8.javabackend.hotel.hotel.dto.AmenitiesDto;
import nhom8.javabackend.hotel.hotel.dto.CreateAmenitiesDto;
import nhom8.javabackend.hotel.hotel.dto.UpdateAmenitiesDto;
import nhom8.javabackend.hotel.hotel.entity.Amenities;
import nhom8.javabackend.hotel.hotel.service.itf.AmenitiesService;

@RestController
@RequestMapping("/api/hotel/amenities")
public class AmenitiesController {
	private AmenitiesService service;
	
	public AmenitiesController(AmenitiesService amenitiesService) {
		service = amenitiesService;
	}
	
	@GetMapping
	public Object findAllAmenities() {
		List<AmenitiesDto> amenities = service.findAllDto();
		return ResponseHandler.getResponse(amenities, HttpStatus.OK);

	}

	@PostMapping("/add-amenities")
	public Object addAmenities(@Valid @RequestBody CreateAmenitiesDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Amenities addedAmenities = service.addNewAmenities(dto);

		return ResponseHandler.getResponse(addedAmenities, HttpStatus.CREATED);
	}

	@PutMapping("/update-amenities")
	public Object updateAmenities(@Valid @RequestBody UpdateAmenitiesDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Amenities updateAmenities = service.updateAmenities(dto);

		return ResponseHandler.getResponse(updateAmenities, HttpStatus.OK);
	}

	@DeleteMapping("/delete-amenities/{amenities-id}")
	public Object deleteAmenities(@PathVariable("amenities-id") Long amenitiesId) {
		service.deleteById(amenitiesId);

		return ResponseHandler.getResponse(HttpStatus.OK);
	}
}
