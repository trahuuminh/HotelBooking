package nhom8.javabackend.hotel.user.dto.message;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateMessageDto {
	@NotNull
	private Long id;
	
	@Email
	private String senderEmail;
	
	private String senderContact;
	
	private String message;
	
	private Long agentId;
}
