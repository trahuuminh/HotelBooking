package nhom8.javabackend.hotel.user.dto.message;

import java.time.LocalDateTime;


public interface MessageDto {
	public Long getId();
	
	public String getSenderEmail();
	
	public String getSenderContact();
	
	public String getMessage();
	
	public LocalDateTime getCreatedAt();
}
