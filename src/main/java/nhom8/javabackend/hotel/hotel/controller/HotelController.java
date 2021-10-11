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
import nhom8.javabackend.hotel.hotel.dto.CreateHotelDto;
import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.hotel.dto.UpdateHotelDto;
import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.hotel.service.itf.HotelService;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
	private HotelService service;

	public HotelController(HotelService hotelService) {
		service = hotelService;

	}

	@GetMapping
	public Object findAllHotel() {
		List<HotelDto> hotels = service.findAllDto();
		return ResponseHandler.getResponse(hotels, HttpStatus.OK);

	}

	@PostMapping("/add-hotel")
	public Object addHotel(@RequestBody CreateHotelDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Hotel addedHotel = service.addNewHotel(dto);

		return ResponseHandler.getResponse(addedHotel, HttpStatus.CREATED);
	}

	@PutMapping("/update-hotel")
	public Object updateHotel(@Valid @RequestBody UpdateHotelDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Hotel updateHotel = service.updateHotel(dto);

		return ResponseHandler.getResponse(updateHotel, HttpStatus.OK);
	}

	@DeleteMapping("/delete-hotel/{hotel-id}")
	public Object deleteHotel(@PathVariable("hotel-id") Long hotelId) {
		service.deleteById(hotelId);

		return ResponseHandler.getResponse(HttpStatus.OK);
	}
}
