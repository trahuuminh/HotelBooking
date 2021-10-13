package nhom8.javabackend.hotel.booking.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import nhom8.javabackend.hotel.hotel.validation.annotation.ExitsUserId;
import nhom8.javabackend.hotel.user.validation.annotation.ExitsHotelId;

@Getter
@Setter
public class CreateBookingDto {
	
	@ExitsUserId
	private Long CustomerId;
	
	@ExitsHotelId
	private Long HotelId;
	
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
