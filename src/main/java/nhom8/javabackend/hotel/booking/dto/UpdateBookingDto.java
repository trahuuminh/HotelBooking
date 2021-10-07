package nhom8.javabackend.hotel.booking.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookingDto {
	
	@NotNull
	private Long id;
	
	@NotBlank
	@Email
	private String bookerEmail;
	
	private String bookerContact;
	
	private String message;
	
	private int rooms;
	
	private int guests;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
}
