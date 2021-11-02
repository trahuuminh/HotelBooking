package nhom8.javabackend.hotel.user.dto.message;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;
import nhom8.javabackend.hotel.hotel.validation.annotation.ExitsUserId;

@Data
public class CreateMessageDto {
	@NotNull
	@Email
	private String senderEmail;
	
	@NotNull
	private String senderContact;
	
	@NotNull
	private String message;
	
	@NotNull
	@ExitsUserId
	private Long agentId;
}
