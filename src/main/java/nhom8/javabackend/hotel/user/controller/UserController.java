package nhom8.javabackend.hotel.user.controller;



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
import nhom8.javabackend.hotel.security.jwt.JwtUtils;
import nhom8.javabackend.hotel.user.dto.AddHotelDto;
import nhom8.javabackend.hotel.user.dto.user.CreateUserDto;
import nhom8.javabackend.hotel.user.dto.user.UpdateUserDto;
import nhom8.javabackend.hotel.user.dto.user.UserDto;
import nhom8.javabackend.hotel.user.entity.User;
import nhom8.javabackend.hotel.user.service.itf.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private UserService service;
	private JwtUtils jwt;
	
	public UserController(UserService userService,JwtUtils jwtUtils) {
		service=userService;
		jwt=jwtUtils;
	}
	
	@GetMapping("/find-all-user")
	public Object findAllUser(@RequestParam("p")Optional<Integer>p) {
		Pageable pageable=PageRequest.of(p.orElse(0),5,Sort.by("id"));
		Page<UserDto> users=service.findAllUser(pageable);
		return ResponseHandler.getResponse(service.pagingFormat(users),HttpStatus.OK);
	}
	
	@PostMapping("/create-user")
	public Object createNewUser(@Valid @RequestBody CreateUserDto dto,BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		User newUser= service.createUser(dto);
		
		return ResponseHandler.getResponse(newUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-user")
	public Object updateUser(@Valid @RequestBody UpdateUserDto dto,BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		User user=service.updateUser(dto);
		
		return ResponseHandler.getResponse(user,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{user-id}")
	public Object deleteUser(@PathVariable("user-id")Long id) {
		if(!service.isExistedId(id))
			return ResponseHandler.getResponse("User doesn't exist",HttpStatus.BAD_REQUEST);
		
		service.deleteUser(id);
		
		return ResponseHandler.getResponse(HttpStatus.OK);
	}
	
	@PostMapping("/add-hotel")
	public Object addHotel(@Valid @RequestBody AddHotelDto dto, BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST); 
		
		User updateUser = service.addHotel(dto);

		return ResponseHandler.getResponse(updateUser, HttpStatus.OK);
	}
	
	
	@PostMapping("/remove-hotel")
	public Object removeHotel(@Valid @RequestBody AddHotelDto dto,
			BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		User updateUser = service.removeHotel(dto);
		
		return ResponseHandler.getResponse(updateUser, HttpStatus.OK);
  }
	@GetMapping("/get-user-details/{user-id}")
	public Object getUserDetails(@PathVariable("user-id") Long id) {
		if(!service.isExistedId(id))
			return ResponseHandler.getResponse("User doesn't exist",HttpStatus.BAD_REQUEST);
		
		UserDto user=service.getUserDetails(id);
		
		return ResponseHandler.getResponse(user,HttpStatus.OK);
	}
	
	@GetMapping("/current-user")
	public Object getUserDetailsFromToken(HttpServletRequest request) {
		String username=jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request));
		
		return ResponseHandler.getResponse(service.getUserByUsername(username),HttpStatus.OK);
	}
}
