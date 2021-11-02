package nhom8.javabackend.hotel.hotel.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import nhom8.javabackend.hotel.hotel.util.Status;
import nhom8.javabackend.hotel.user.validation.annotation.ExitsHotelId;

@Getter
@Setter
public class UpdateHotelDto {
	
	@NotNull
	@ExitsHotelId
	private Long id;
	
	private String title;

	private String content;

	private Status status;

	private String price;

	private boolean isNegotiable;

	private String condition;

	private String contactNumber;

	private String termsAndCondition;
}
