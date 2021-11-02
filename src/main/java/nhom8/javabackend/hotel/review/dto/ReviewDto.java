package nhom8.javabackend.hotel.review.dto;

import java.time.LocalDateTime;

import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.user.entity.User;

public interface ReviewDto {
	
	public Long getId();
	
	public String getTitle();
	
	public String getText();
	
	public LocalDateTime getReviewDate();
	
	public int getServiceRating();
	
	public int getRoomRating();
	
	public int getCleannessRating();
	
	public int getFoodRating();
	
	public Hotel getHotel();
	
	public User getAuthor();
	
	
}
