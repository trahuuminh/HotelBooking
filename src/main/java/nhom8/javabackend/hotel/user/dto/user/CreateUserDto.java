package nhom8.javabackend.hotel.user.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import lombok.Data;
import nhom8.javabackend.hotel.user.validation.annotation.ExistEmail;
import nhom8.javabackend.hotel.user.validation.annotation.ExistUsername;

@Data
public class CreateUserDto {
	
	
	@Nullable
	private String firstName;
	
	@Nullable
	private String lastName;
	
	@ExistUsername
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@ExistEmail
	@Email
	@NotBlank
	private String email;
	
	@Nullable
	private String cellNumber;
	
	@Nullable
	private String dateOfBirth;
	
	@Nullable
	private String gender;
	
	@Nullable
	private String content;
	
	@Nullable
	private String language;
	
	@Nullable
	private String facebook;
	
	@Nullable
	private String twitter;
	
	@Nullable
	private String linkedin;
	
	@Nullable
	private String instagram;
	
	@Nullable
	private String pinterest;
}
