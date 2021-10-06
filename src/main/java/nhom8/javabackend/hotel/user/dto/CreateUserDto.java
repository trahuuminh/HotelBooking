package nhom8.javabackend.hotel.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateUserDto {
	
	@NotBlank
	private String role;
	
	private String firstName;
	
	private String lastName;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@Email
	@NotBlank
	private String email;
	
	private String cellNumber;
	
	private String dateOfBirth;
	
	private String gender;
	
	private String content;
	
	private String language;
	
	private String facebook;
	
	private String twitter;
	
	private String linkedin;
	
	private String instagram;
	
	private String pinterest;
}
