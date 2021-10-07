package nhom8.javabackend.hotel.location.controller;

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

import nhom8.javabackend.hotel.booking.dto.BookingDto;
import nhom8.javabackend.hotel.booking.dto.CreateBookingDto;
import nhom8.javabackend.hotel.booking.dto.UpdateBookingDto;
import nhom8.javabackend.hotel.booking.entity.Booking;
import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.location.service.itf.LocationService;

@RestController
@RequestMapping("/api/location")
public class LocationController {
	private LocationService service;
	
	public LocationController(LocationService locationservice) {
		service = locationservice;
	}
	
	@GetMapping
	public Object findAllLocation() {
		List<LocationDto> users = service.findAllDto();
		return ResponseHandler.getResponse(users, HttpStatus.OK);

	}

	@PostMapping("/add-location")
	public Object addLocation(@Valid @RequestBody CreateLocationDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Booking addedLocation = service.addNewLocation(dto);

		return ResponseHandler.getResponse(addedLocation, HttpStatus.CREATED);
	}

	@PutMapping("/update-location")
	public Object updateLocation(@Valid @RequestBody UpdateLocationDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Booking updateLocation = service.updateLocation(dto);

		return ResponseHandler.getResponse(updateLocation, HttpStatus.OK);
	}

	@DeleteMapping("/delete-location//{location-id}")
	public Object deleteLocation(@PathVariable("location-id") Long locationId) {
		service.deleteById(locationId);

		return ResponseHandler.getResponse(HttpStatus.OK);
	}
}
