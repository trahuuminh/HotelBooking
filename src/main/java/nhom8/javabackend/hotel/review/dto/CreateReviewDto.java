package nhom8.javabackend.hotel.review.dto;


import javax.validation.constraints.NotNull;

import lombok.Data;
import nhom8.javabackend.hotel.review.validation.annotation.Rating;
import nhom8.javabackend.hotel.user.validation.annotation.ExitsHotelId;

@Data
public class CreateReviewDto {
	
	@ExitsHotelId
	private Long hotelId;
	
	private String title;
	
	@NotNull
	private String text;
	
	@Rating
	private int serviceRating;
	
	@Rating
	private int roomRating;
	
	@Rating
	private int CleannessRating;
	
	@Rating
	private int foodRating;
}
