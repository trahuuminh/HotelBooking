package nhom8.javabackend.hotel.booking.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import nhom8.javabackend.hotel.booking.dto.BookingDto;
import nhom8.javabackend.hotel.booking.dto.CreateBookingDto;
import nhom8.javabackend.hotel.booking.dto.UpdateBookingDto;
import nhom8.javabackend.hotel.booking.entity.Booking;
import nhom8.javabackend.hotel.booking.service.itf.BookingService;
import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;

@RestController
@RequestMapping("api/booking")
public class BookingController {
	private BookingService service;

	public BookingController(BookingService bookingService) {
		service = bookingService;
	}

	@GetMapping
	public Object findAllBooking(@RequestParam("p") Optional<Integer> p) {
		Pageable pageable=PageRequest.of(p.orElse(0), 5,Sort.by("id"));
		Page<BookingDto> bookings = service.findAllBooking(pageable);
		return ResponseHandler.getResponse(service.pagingFormat(bookings), HttpStatus.OK);

	}

	@PostMapping("/add-booking")
	public Object addBooking(@Valid @RequestBody CreateBookingDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Booking addedBooking = service.addedNewBooking(dto);

		return ResponseHandler.getResponse(addedBooking, HttpStatus.CREATED);
	}

	@PutMapping("/update-booking")
	public Object updateBooking(@Valid @RequestBody UpdateBookingDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Booking updateBooking = service.updateBooking(dto);

		return ResponseHandler.getResponse(updateBooking, HttpStatus.OK);
	}

	@DeleteMapping("/delete-booking//{booking-id}")
	public Object deleteBooking(@PathVariable("booking-id") Long bookingId) {
		if(!service.isExistedId(bookingId))
			return ResponseHandler.getResponse("Booking doesn't exist",HttpStatus.BAD_REQUEST);
		
		service.deleteById(bookingId);

		return ResponseHandler.getResponse(HttpStatus.OK);
	}

}
