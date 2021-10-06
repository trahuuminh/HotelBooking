package nhom8.javabackend.hotel.user.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import nhom8.javabackend.hotel.user.util.Role;

@Data
public class UpdateUserDto {
	@NotNull
	private Long id;
	
	@NotBlank
	private Role role;
	
	private String firstName;
	
	private String lastName;
	
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
