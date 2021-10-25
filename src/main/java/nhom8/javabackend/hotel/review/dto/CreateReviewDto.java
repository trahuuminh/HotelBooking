package nhom8.javabackend.hotel.review.dto;


import javax.validation.constraints.NotNull;

import lombok.Data;
import nhom8.javabackend.hotel.user.validation.annotation.ExitsHotelId;

@Data
public class CreateReviewDto {
	
	@ExitsHotelId
	private Long hotelId;
	
	private String title;
	
	@NotNull
	private String text;
	
	
	private int serviceRating;
	
	private int roomRating;
	
	private int CleannessRating;
	
	private int foodRating;
}
