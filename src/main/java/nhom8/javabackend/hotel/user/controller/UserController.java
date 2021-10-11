package nhom8.javabackend.hotel.user.controller;

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
import nhom8.javabackend.hotel.user.dto.user.CreateUserDto;
import nhom8.javabackend.hotel.user.dto.user.UpdateUserDto;
import nhom8.javabackend.hotel.user.dto.user.UserDto;
import nhom8.javabackend.hotel.user.entity.User;
import nhom8.javabackend.hotel.user.service.itf.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private UserService service;
	
	public UserController(UserService userService) {
		service=userService;
	}
	
	@GetMapping("/find-all-user")
	public Object findAllUser() {
		List<UserDto> users=service.findAllUser();
		return ResponseHandler.getResponse(users,HttpStatus.OK);
	}
	
	@PostMapping("/create-user")
	public Object createNewUser(@Valid @RequestBody CreateUserDto dto,BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		User user=service.createUser(dto);
		
		return ResponseHandler.getResponse(user,HttpStatus.CREATED);
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
		service.deleteUser(id);
		
		return ResponseHandler.getResponse(HttpStatus.OK);
	}
	
	@GetMapping("/get-user-details/{user-id}")
	public Object getUserDetails(@PathVariable("user-id") Long id) {
		User user=service.getUserDetails(id);
		
		return ResponseHandler.getResponse(user,HttpStatus.OK);
	}
}
