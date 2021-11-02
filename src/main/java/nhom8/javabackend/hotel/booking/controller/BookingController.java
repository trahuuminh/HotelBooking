package nhom8.javabackend.hotel.booking.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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
import nhom8.javabackend.hotel.security.jwt.JwtUtils;
import nhom8.javabackend.hotel.user.entity.User;
import nhom8.javabackend.hotel.user.service.itf.UserService;
import nhom8.javabackend.hotel.user.util.Role;

@RestController
@RequestMapping("api/booking")
public class BookingController {
	private BookingService service;
	private JwtUtils jwt;
	private UserService userService;
	
	public BookingController(BookingService bookingService, JwtUtils Jwt,UserService UserService) {
		service = bookingService;
		jwt=Jwt;
		userService=UserService;
	}

	@GetMapping
	public Object findAllBooking(@RequestParam("p") Optional<Integer> p, HttpServletRequest request) {
		try {
			if(jwt.getJwtTokenFromRequest(request)==null)
				return ResponseHandler.getResponse("please sign in first",HttpStatus.BAD_REQUEST);
			else if(!userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))).getRole().equals(Role.ADMIN))
				return ResponseHandler.getResponse("you're not allowed to find all booking",HttpStatus.BAD_REQUEST);
			
			Pageable pageable=PageRequest.of(p.orElse(0), 22,Sort.by("id"));
			Page<BookingDto> bookings = service.findAllBooking(pageable);
			return ResponseHandler.getResponse(service.pagingFormat(bookings), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse("Unreachable token !",HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/add-booking")
	public Object addBooking(@Valid @RequestBody CreateBookingDto dto, BindingResult errors, HttpServletRequest request) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		else if(jwt.getJwtTokenFromRequest(request)==null)
			return ResponseHandler.getResponse("Please sign in before booking hotel",HttpStatus.BAD_REQUEST);
		
		Booking addedBooking = service.addedNewBooking(dto, userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))));

		return ResponseHandler.getResponse(addedBooking, HttpStatus.CREATED);
	}

	@PutMapping("/update-booking")
	public Object updateBooking(@Valid @RequestBody UpdateBookingDto dto, BindingResult errors,HttpServletRequest request) {
		try {
			if (errors.hasErrors())
				return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
			else if(jwt.getJwtTokenFromRequest(request)==null)
				return ResponseHandler.getResponse("please sign in first",HttpStatus.BAD_REQUEST);
			else if(userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))) != service.getBookingByBookingId(dto.getId()).getCustomer())
				return ResponseHandler.getResponse("you're not allowed to update this booking",HttpStatus.BAD_REQUEST);

			Booking updateBooking = service.updateBooking(dto);

			return ResponseHandler.getResponse(updateBooking, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse("Unreachable token !",HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete-booking/{booking-id}")
	public Object deleteBooking(@PathVariable("booking-id") Long bookingId,HttpServletRequest request) {
		try {
			if(jwt.getJwtTokenFromRequest(request)==null)
				return ResponseHandler.getResponse("please sign in first",HttpStatus.BAD_REQUEST);
			
			else if(!service.isExistedId(bookingId))
				return ResponseHandler.getResponse("Booking doesn't exist",HttpStatus.BAD_REQUEST);
			
			else if(userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))) != service.getBookingByBookingId(bookingId).getCustomer())
				return ResponseHandler.getResponse("you're not allowed to delete this booking",HttpStatus.BAD_REQUEST);	
			
			service.deleteById(bookingId);

			return ResponseHandler.getResponse(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse("Unreachable token !",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/agent/{agent-id}")
	public Object findAllBookingByAgentId(@PathVariable("agent-id") Long agentId, @RequestParam("page") Optional<Integer> p,HttpServletRequest request) {
		try {
			if(jwt.getJwtTokenFromRequest(request)==null)
				return ResponseHandler.getResponse("please sign in first",HttpStatus.BAD_REQUEST);
			
			User currentUser =userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			
			if(!userService.isExistedId(agentId))
				return ResponseHandler.getResponse("User doesn't exist",HttpStatus.BAD_REQUEST);
			
			else if(!currentUser.getRole().equals(Role.ADMIN) && currentUser.getId() != agentId)
				return ResponseHandler.getResponse("you're not allowed to find all of this user's bookings",HttpStatus.BAD_REQUEST);
			
			Pageable pageable=PageRequest.of(p.orElse(0), 22,Sort.by("id"));
			Page<BookingDto> bookings=service.findAllBookingByAgentId(agentId, pageable);
			return ResponseHandler.getResponse(service.pagingFormat(bookings),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse("unreachable token !",HttpStatus.BAD_REQUEST);
		}
	}
}
