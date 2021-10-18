package nhom8.javabackend.hotel.hotel.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import nhom8.javabackend.hotel.hotel.util.Status;
import nhom8.javabackend.hotel.hotel.validation.annotation.ExitsUserId;

@Getter
@Setter
public class CreateHotelDto {
	@NotNull
	@ExitsUserId
	private Long agentId;
	
	@NotNull
	private Long amenitiesId;
	
	@NotNull
	private Long locationId;
	
	private String title;

	private String slug;

	private String content;

	@NotNull
	private Status status;

	@NotNull
	private String price;

	private boolean isNegotiable;

	private String condition;

	@NotNull
	private int rating;

	@NotNull
	private int ratingCount;

	@NotNull
	private String contactNumber;

	private String termsAndCondition;
}
