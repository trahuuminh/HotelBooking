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
import nhom8.javabackend.hotel.user.dto.userimage.CreateUserImageDto;
import nhom8.javabackend.hotel.user.dto.userimage.UpdateUserImageDto;
import nhom8.javabackend.hotel.user.dto.userimage.UserImageDto;
import nhom8.javabackend.hotel.user.entity.UserImage;
import nhom8.javabackend.hotel.user.service.itf.UserImageService;

@RestController
@RequestMapping("/api/user-image")
public class UserImageController {
	
	private UserImageService service;
	
	public UserImageController(UserImageService userImageService) {
		service=userImageService;
	}
	
	@GetMapping("/find-all-user-image")
	public Object findAllUserImage() {
		List<UserImageDto> userImages=service.findAllUserImageDto();
		return ResponseHandler.getResponse(userImages,HttpStatus.OK);
	}
	
	@PostMapping("/update-user-image")
	public Object createNewUserImage(@Valid @RequestBody CreateUserImageDto dto,BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		UserImage userImage=service.createNewUserImage(dto);
		
		return ResponseHandler.getResponse(userImage,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-user-image")
	public Object updateUserImage(@Valid @RequestBody UpdateUserImageDto dto, BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		UserImage userImage=service.updateUserImage(dto);
		
		return ResponseHandler.getResponse(userImage,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{userimage-id}")
	public Object deleteUserImage(@PathVariable("userimage-id")Long id) {
		service.deleteUserImage(id);
		
		return ResponseHandler.getResponse(HttpStatus.OK);
	}
}
