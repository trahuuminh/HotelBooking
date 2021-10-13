package nhom8.javabackend.hotel.review.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Data;
import nhom8.javabackend.hotel.review.validation.annotation.ExistReviewId;

@Data
public class UpdateReviewDto {
	@NotNull
	@ExistReviewId
	private Long id;
	
	private String title;
	
	@NotNull
	private String text;
	
	private LocalDateTime reviewDate;
	
	private int serviceRating;
	
	private int roomRating;
	
	private int CleannessRating;
	
	private int foodRating;
}
