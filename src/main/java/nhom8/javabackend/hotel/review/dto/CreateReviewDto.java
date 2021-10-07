package nhom8.javabackend.hotel.review.dto;


import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateReviewDto {
	
	private String title;
	
	@NotNull
	private String text;
	
	
	private int serviceRating;
	
	private int roomRating;
	
	private int CleannessRating;
	
	private int foodRating;
}
