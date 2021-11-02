package nhom8.javabackend.hotel.review.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Data;
import nhom8.javabackend.hotel.review.validation.annotation.ExistReviewId;
import nhom8.javabackend.hotel.review.validation.annotation.Rating;

@Data
public class UpdateReviewDto {
	@NotNull
	@ExistReviewId
	private Long id;
	
	private String title;
	
	@NotNull
	private String text;
	
	private LocalDateTime reviewDate;
	
	@Rating
	private int serviceRating;
	
	@Rating
	private int roomRating;
	
	@Rating
	private int CleannessRating;
	
	@Rating
	private int foodRating;
}
