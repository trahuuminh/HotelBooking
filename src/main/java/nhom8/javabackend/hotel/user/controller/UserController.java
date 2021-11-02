package nhom8.javabackend.hotel.user.controller;


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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.s3.service.StorageService;
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
import nhom8.javabackend.hotel.user.util.Role;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST}, origins = "*", allowedHeaders = "*")
public class UserController {
	private final String imagesDir = "user-images/";
	private UserService service;
	private JwtUtils jwt;
	private UserImageService userImageService;
	private final AuthenticationManager authenticationManager;
	private StorageService storageService;
	
	public UserController(UserService userService,
						JwtUtils jwtUtils,
						UserImageService userimageService,
						AuthenticationManager authManager,
						StorageService s3StorageService) {
		service=userService;
		jwt=jwtUtils;
		userImageService=userimageService;
		authenticationManager =authManager;
		storageService = s3StorageService;
	}
	
	@GetMapping("/find-all-user")
	public Object findAllUser(@RequestParam("p")Optional<Integer>p) {
			
			Pageable pageable=PageRequest.of(p.orElse(0),12,Sort.by("id"));
			Page<UserDto> users=service.findAllUser(pageable);
			return ResponseHandler.getResponse(service.pagingFormat(users),HttpStatus.OK);
		
	}
	
	@PostMapping("/create-user")
	public Object createNewUser(@Valid @RequestBody CreateUserDto dto,BindingResult errors, HttpServletRequest request) {
		try {
			if(errors.hasErrors())
				return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
			
			else if(jwt.getJwtTokenFromRequest(request)==null)
				return ResponseHandler.getResponse("please sign in first before create user",HttpStatus.BAD_REQUEST);
			
			else if(service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))).getRole().equals(Role.ADMIN) || 
					service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))).getCellNumber().equals("secretadmin")) {
				User newUser= service.createUser(dto);	
				return ResponseHandler.getResponse(newUser, HttpStatus.CREATED);
			}
			
			return ResponseHandler.getResponse("you're not allowed to create this user",HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse("Unreachable token !",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update-user")
	public Object updateUser(@Valid @RequestBody UpdateUserDto dto,BindingResult errors,HttpServletRequest request) {
		try {
			if(errors.hasErrors())
				return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
			
			else if(jwt.getJwtTokenFromRequest(request)==null)
				return ResponseHandler.getResponse("please sign in first before update",HttpStatus.BAD_REQUEST);
			
			User currentUser=service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			
			if(currentUser.getRole().equals(Role.ADMIN) || currentUser.getId()==dto.getId()) {
				User user=service.updateUser(dto);
				return ResponseHandler.getResponse(user,HttpStatus.OK);
			}
			
			return ResponseHandler.getResponse("you're not allowed to update this user",HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse("Unreachable token !",HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{user-id}")
	public Object deleteUser(@PathVariable("user-id")Long id,HttpServletRequest request) {
		try {
			if(jwt.getJwtTokenFromRequest(request)==null)
				return ResponseHandler.getResponse("please sign in first before delete",HttpStatus.BAD_REQUEST);
			
			else if(!service.isExistedId(id))
				return ResponseHandler.getResponse("User doesn't exist",HttpStatus.BAD_REQUEST);
			
			else if(!service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))).getRole().equals(Role.ADMIN))
				return ResponseHandler.getResponse("you're not allowed to delete this user",HttpStatus.BAD_REQUEST);
			
			else if(service.getUserByUserId(id).getListedPost().size()>0)
				return ResponseHandler.getResponse("Please delete all this user's hotels first before delete this user !",HttpStatus.BAD_REQUEST);
			
				service.deleteUser(id);
				return ResponseHandler.getResponse(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse("Unreachable token !",HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping("/add-favourite-hotel")
	public Object addHotelToFavouritePost(@Valid @RequestBody AddHotelDto dto, BindingResult errors,HttpServletRequest request) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST); 
		else if(jwt.getJwtTokenFromRequest(request)==null)
			return ResponseHandler.getResponse("please sign in first if you want to add this hotel to your favourite hotel list",HttpStatus.BAD_REQUEST);
		
		User updateUser = service.addFavouriteHotel(dto,service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))));

		return ResponseHandler.getResponse(updateUser, HttpStatus.OK);
	}
		
	@PostMapping("/remove-favourite-hotel")
	public Object removeHotelFromFavouritePost(@Valid @RequestBody AddHotelDto dto,BindingResult errors,HttpServletRequest request) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		User updateUser = service.removeFavouriteHotel(dto,service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))));
		
		return ResponseHandler.getResponse(updateUser, HttpStatus.OK);
	}
	
	@GetMapping("/get-user-details/{user-id}")
	public Object getUserDetails(@PathVariable("user-id") Long id) {
			if(!service.isExistedId(id))
				return ResponseHandler.getResponse("User doesn't exist",HttpStatus.BAD_REQUEST);
		
			try {
				UserDto user = service.getUserDetails(id);
				
				return ResponseHandler.getResponse(user,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
			}
	}
	
	@GetMapping("/current-user")
	public Object getCurrentUser(HttpServletRequest request) {
		
		
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
		
		if(jwt.getJwtTokenFromRequest(request)==null)
			return ResponseHandler.getResponse("please sign in first before create user",HttpStatus.BAD_REQUEST);
		try {
			String url = storageService.uploadFile(file, imagesDir);
			CreateUserImageDto dto = new CreateUserImageDto();
			dto.setUrl(url);
			dto.setName(url.substring(url.lastIndexOf(imagesDir)));
			
			UserImage userImage = userImageService.createNewUserImage(dto);
			
			User user = service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			
			return ResponseHandler.getResponse(service.setUserProfilePic(user, userImage).getProfilePic(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/upload-user-cover-pic")
	public Object uploadUserCoverPic(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

		if(jwt.getJwtTokenFromRequest(request)==null)
			return ResponseHandler.getResponse("please sign in first before create user",HttpStatus.BAD_REQUEST);
		try {
			
			String url = storageService.uploadFile(file, imagesDir);
			
			CreateUserImageDto dto = new CreateUserImageDto();

			dto.setUrl(url);
			dto.setName(url.substring(url.lastIndexOf(imagesDir)));
			
			UserImage userImage = userImageService.createNewUserImage(dto);
			
			User user = service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			
			return ResponseHandler.getResponse(service.setUserCoverPic(user, userImage).getCoverPic(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete-user-profile-pic")
	public Object deleteUserProfilePic(HttpServletRequest request) {		
		try {
			User user = service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			UserImage userImage = user.getProfilePic();
			
			if(storageService.deleteFile(userImage.getName())) {
				service.setUserProfilePic(user, null);
				userImageService.deleteUserImage(userImage.getId());
			}

			return ResponseHandler.getResponse(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/delete-user-cover-pic")
	public Object deleteUserCoverPic(HttpServletRequest request) {
		try {
			User user = service.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			UserImage userImage = user.getCoverPic();
			
			if(storageService.deleteFile(userImage.getName())) {
				service.setUserCoverPic(user, null);
				userImageService.deleteUserImage(userImage.getId());
			}

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
