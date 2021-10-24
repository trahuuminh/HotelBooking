package nhom8.javabackend.hotel.user.controller;



import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.multipart.MultipartFile;

import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.security.jwt.JwtUtils;
import nhom8.javabackend.hotel.user.dto.AddHotelDto;
import nhom8.javabackend.hotel.user.dto.user.CreateUserDto;
import nhom8.javabackend.hotel.user.dto.user.UpdateUserDto;
import nhom8.javabackend.hotel.user.dto.user.UserDto;
import nhom8.javabackend.hotel.user.dto.userimage.CreateUserImageDto;
import nhom8.javabackend.hotel.user.entity.User;
import nhom8.javabackend.hotel.user.entity.UserImage;
import nhom8.javabackend.hotel.user.service.itf.UserImageService;
import nhom8.javabackend.hotel.user.service.itf.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private final String uploadDir="/src/main/resources/static/user-images/";
	private UserService service;
	private JwtUtils jwt;
	private UserImageService userImageService;
	private final AuthenticationManager authenticationManager;
	
	public UserController(UserService userService,JwtUtils jwtUtils,UserImageService userimageService,AuthenticationManager authManager) {
		service=userService;
		jwt=jwtUtils;
		userImageService=userimageService;
		authenticationManager =authManager;
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
		else if(service.getUserByUserId(id).getListedPost().size()>0)
			return ResponseHandler.getResponse("Please delete all this user's hotels first before delete this user !",HttpStatus.BAD_REQUEST);
		
		service.deleteUser(id);
		
		return ResponseHandler.getResponse(HttpStatus.OK);
	}
	
	@PostMapping("/add-hotel")
	public Object addHotelToFavouritePost(@Valid @RequestBody AddHotelDto dto, BindingResult errors,HttpServletRequest request) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST); 
		else if(jwt.getJwtTokenFromRequest(request)==null)
			return ResponseHandler.getResponse("please sign in first if you want to add this hotel to your favourite hotel list",HttpStatus.BAD_REQUEST);
		
		User updateUser = service.addHotel(dto,service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))));

		return ResponseHandler.getResponse(updateUser, HttpStatus.OK);
	}
	
	
	@PostMapping("/remove-hotel")
	public Object removeHotelFromFavouritePost(@Valid @RequestBody AddHotelDto dto,BindingResult errors,HttpServletRequest request) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		User updateUser = service.removeHotel(dto,service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))));
		
		return ResponseHandler.getResponse(updateUser, HttpStatus.OK);
  }
	@GetMapping("/get-user-details/{user-id}")
	public Object getUserDetails(@PathVariable("user-id") Long id) {
			
		try {
			UserDto user=service.getUserDetails(id);
			
			return ResponseHandler.getResponse(user,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHandler.getResponse("User doesn't exist",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/current-user")
	public Object getUserDetailsFromToken(HttpServletRequest request) {
		try {
			String username=jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request));
			
			return ResponseHandler.getResponse(service.getUserDtoByUsername(username),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHandler.getResponse("Unreachable token !",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/upload-user-profile-pic")
	public Object uploadUserProfilePic(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		try {
			Calendar date= Calendar.getInstance();
			String fileName = file.getOriginalFilename()+"-"+date.getTimeInMillis();
			
			String userDirectory=Paths.get("").toAbsolutePath().toString();
			
			Path folderPath = Paths.get(userDirectory + uploadDir);
			
			if(!Files.exists(folderPath)) {
				Files.createDirectories(folderPath);
			}
			
			Path path = Paths.get(userDirectory + uploadDir + fileName);
			
			Files.write(path, file.getBytes());
			CreateUserImageDto dto=new CreateUserImageDto(userDirectory + uploadDir + fileName,fileName);
			UserImage userImage=userImageService.createNewUserImage(dto);
			
			User user=service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			
			service.setUserProfilePic(user, userImage);
			return ResponseHandler.getResponse(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/upload-user-cover-pic")
	public Object uploadUserCoverPic(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		try {
			Calendar date= Calendar.getInstance();
			String fileName = file.getOriginalFilename()+"-"+date.getTimeInMillis();
			
			String userDirectory=Paths.get("").toAbsolutePath().toString();
			
			Path folderPath = Paths.get(userDirectory + uploadDir);
			
			if(!Files.exists(folderPath)) {
				Files.createDirectories(folderPath);
			}
			
			Path path = Paths.get(userDirectory + uploadDir + fileName);
			
			Files.write(path, file.getBytes());
			CreateUserImageDto dto=new CreateUserImageDto(userDirectory + uploadDir + fileName,fileName);
			UserImage userImage=userImageService.createNewUserImage(dto);
			
			User user=service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			
			service.setUserCoverPic(user, userImage);
			return ResponseHandler.getResponse(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete-user-profile-pic")
	public Object deleteUserProfilePic(HttpServletRequest request) {		
		try {
			User user=service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			Long userImageId=user.getProfilePic().getId();
			File file=new File(user.getProfilePic().getPath());
			file.delete();
			service.setUserProfilePic(user, null);
			userImageService.deleteUserImage(userImageId);
			return ResponseHandler.getResponse(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/delete-user-cover-pic")
	public Object deleteUserCoverPic(HttpServletRequest request) {
		try {
			User user=service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			Long userImageId=user.getCoverPic().getId();
			File file=new File(user.getCoverPic().getPath()); 
			file.delete();
			service.setUserCoverPic(user, null);
			userImageService.deleteUserImage(userImageId);
			return ResponseHandler.getResponse(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/register")
	public Object register(@Valid @RequestBody CreateUserDto dto,BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		service.register(dto);
		Authentication auth=null;
		
		try {
			auth= authenticationManager.authenticate
					(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(auth);
			String token=jwt.generateJwtToken(auth);
			return ResponseHandler.getResponse(token,HttpStatus.CREATED);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
	}
}
