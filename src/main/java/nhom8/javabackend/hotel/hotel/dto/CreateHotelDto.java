package nhom8.javabackend.hotel.hotel.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import nhom8.javabackend.hotel.hotel.util.Status;

@Getter
@Setter
public class CreateHotelDto {
	
	@NotNull
	private Long amenitiesId;
	
	@NotNull
	private Long locationId;
	
	@NotNull
	private String title;
	
	@NotNull
	private String slug;

	private String content;

	@NotNull
	private Status status;

	@NotNull
	private String price;

	private boolean isNegotiable;

	private String condition;

	private String contactNumber;

	private String termsAndCondition;
}
