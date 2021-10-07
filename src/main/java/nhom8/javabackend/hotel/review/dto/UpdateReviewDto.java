package nhom8.javabackend.hotel.review.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateReviewDto {
	@NotNull
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
