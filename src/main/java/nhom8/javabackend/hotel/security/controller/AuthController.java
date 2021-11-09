package nhom8.javabackend.hotel.security.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.security.dto.LoginDto;
import nhom8.javabackend.hotel.security.dto.RegisterDto;
import nhom8.javabackend.hotel.security.jwt.JwtUtils;
import nhom8.javabackend.hotel.user.service.itf.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {	
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	private static final Logger logger= LoggerFactory.getLogger(Jwts.class);
	private UserService userService;
	
	public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils, UserService userService) {
		authenticationManager =authManager;
		this.jwtUtils=jwtUtils;
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public Object login(@Valid @RequestBody LoginDto dto, BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		Authentication auth=null;
		
		try {
			auth= authenticationManager.authenticate
								(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(auth);
			String token=jwtUtils.generateJwtToken(auth);
			return ResponseHandler.getResponse(token,HttpStatus.OK);
		} catch (Exception e) {
			logger.debug("{} has been logged in with wrong password: {}",dto.getUsername(),e.getMessage());
		}
		
		return ResponseHandler.getResponse("Username or Password is invalid.",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/register")
	public Object register(@Valid @RequestBody RegisterDto dto,BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		userService.register(dto);
		Authentication auth=null;
		
		try {
			auth= authenticationManager.authenticate
					(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(auth);
			String token=jwtUtils.generateJwtToken(auth);
			return ResponseHandler.getResponse(token,HttpStatus.CREATED);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
	}
}
