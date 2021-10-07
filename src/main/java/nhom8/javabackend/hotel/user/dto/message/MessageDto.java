package nhom8.javabackend.hotel.user.dto.message;

import nhom8.javabackend.hotel.user.entity.User;

public interface MessageDto {
	public String getSenderEmail();
	
	public String getSenderContact();
	
	public String getMessage();
	
	public User getAgent();
}
