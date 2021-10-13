package nhom8.javabackend.hotel.user.dto.message;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;
import nhom8.javabackend.hotel.user.validation.annotation.ExistMessageId;

@Data
public class UpdateMessageDto {
	@NotNull
	@ExistMessageId
	private Long id;
	
	@Email
	private String senderEmail;
	
	private String senderContact;
	
	private String message;
	
	private Long agentId;
}
