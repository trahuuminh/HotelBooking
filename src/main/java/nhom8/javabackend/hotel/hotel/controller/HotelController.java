package nhom8.javabackend.hotel.hotel.controller;

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

import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.hotel.dto.CreateHotelDto;
import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.hotel.dto.UpdateHotelDto;
import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.hotel.service.itf.HotelService;
import nhom8.javabackend.hotel.security.jwt.JwtUtils;
import nhom8.javabackend.hotel.user.entity.User;
import nhom8.javabackend.hotel.user.service.itf.UserService;
import nhom8.javabackend.hotel.user.util.Role;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
	private HotelService service;
	private JwtUtils jwt;
	private UserService userService;
	
	public HotelController(HotelService hotelService,
							UserService UserService,
							JwtUtils Jwt) {
		service = hotelService;
		jwt=Jwt;
		userService = UserService;
	}

	@GetMapping
	public Object findAllHotels(@RequestParam("p") Optional<Integer> p) {
		Pageable pageable= PageRequest.of(p.orElse(0), 12,Sort.by("id"));
		Page<HotelDto> hotels = service.findAllHotel(pageable);
		return ResponseHandler.getResponse(service.pagingFormat(hotels), HttpStatus.OK);

	}
	
	@GetMapping("/{slug}")
	public Object findHotel(@PathVariable("slug") String slug) {
		HotelDto hotels = service.getHotelBySlugName(slug);
		return ResponseHandler.getResponse(hotels, HttpStatus.OK);
	}
	
	@GetMapping("/top-booked-hotel")
	public Object findHotelByMostBooking(@RequestParam("p") Optional<Integer> p) {
		Pageable pageable= PageRequest.of(p.orElse(0), 12);
		Page<HotelDto> hotels = service.FindHotelByMostBooking(pageable);
		return ResponseHandler.getResponse(service.pagingFormat(hotels), HttpStatus.OK);

	}

	@PostMapping("/add-hotel")
	public Object addNewHotel(@Valid @RequestBody CreateHotelDto dto, BindingResult errors, HttpServletRequest request) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		else if(jwt.getJwtTokenFromRequest(request) == null)
			return ResponseHandler.getResponse("Please sign in first if you want to post your hotel", HttpStatus.BAD_REQUEST);
		
		HotelDto foundedHotel = service.getHotelBySlugName(dto.getSlug());
		if(foundedHotel != null) return ResponseHandler.getResponse("Hotel's slug already existed.", HttpStatus.BAD_REQUEST);
		
		Hotel addedHotel = service.addNewHotel(dto,userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))));

		return ResponseHandler.getResponse(addedHotel, HttpStatus.CREATED);
	}

	@PutMapping("/update-hotel")
	public Object updateHotel(@Valid @RequestBody UpdateHotelDto dto, BindingResult errors,HttpServletRequest request) {
		try {
			if (errors.hasErrors())
				return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
			else if(jwt.getJwtTokenFromRequest(request) == null)
				return ResponseHandler.getResponse("Please sign in first", HttpStatus.BAD_REQUEST);
			
			User currentUser = userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			
			if(currentUser.getId() != service.getHotelByHotelId(dto.getId()).getAgent().getId() && !currentUser.getRole().equals(Role.ADMIN))
				return ResponseHandler.getResponse("You're not allowed to update this hotel",HttpStatus.BAD_REQUEST);
			
			Hotel updateHotel = service.updateHotel(dto);

			return ResponseHandler.getResponse(updateHotel, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete-hotel/{hotel-id}")
	public Object deleteHotel(@PathVariable("hotel-id") Long hotelId, HttpServletRequest request) {
		try {
			if(jwt.getJwtTokenFromRequest(request)==null)
				return ResponseHandler.getResponse("please sign in first ",HttpStatus.BAD_REQUEST);
			
			User currentUser =userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			
			if(currentUser.getId() != service.getHotelByHotelId(hotelId).getAgent().getId() && !currentUser.getRole().equals(Role.ADMIN))
				return ResponseHandler.getResponse("you're not allowed to delete this hotel",HttpStatus.BAD_REQUEST);
			
			if(!service.isExistedId(hotelId))
				return ResponseHandler.getResponse("Hotel doesn't exist",HttpStatus.BAD_REQUEST);
			else if(service.getHotelByHotelId(hotelId).getBookings().size()>0)
				return ResponseHandler.getResponse("Please delete all bookings before delete hotel !",HttpStatus.BAD_REQUEST);
			
			service.deleteById(hotelId);

			return ResponseHandler.getResponse(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse("Unreachable token !",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/best-rated-hotel")
	public Object getBestHotels(@RequestParam("page")Optional<Integer> page ) {
		
		Pageable pageable= PageRequest.of(page.orElse(0), 12,Sort.by("rating").descending());
		Page<HotelDto> hotels=service.findAllHotel(pageable);
		return ResponseHandler.getResponse(service.pagingFormat(hotels),HttpStatus.OK);
	}
}
