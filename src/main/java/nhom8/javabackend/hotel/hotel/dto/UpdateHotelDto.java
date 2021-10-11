package nhom8.javabackend.hotel.hotel.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import nhom8.javabackend.hotel.hotel.util.Status;

@Getter
@Setter
public class UpdateHotelDto {
	
	@NotNull
	private Long id;
	
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
