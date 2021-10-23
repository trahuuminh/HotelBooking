package nhom8.javabackend.hotel.user.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import nhom8.javabackend.hotel.hotel.validation.annotation.ExitsUserId;

@Data
public class UpdateUserDto {
	@NotNull
	@ExitsUserId
	private Long id;
	
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
