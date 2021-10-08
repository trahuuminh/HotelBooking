package nhom8.javabackend.hotel.hotel.dto;

import java.util.Set;

import nhom8.javabackend.hotel.hotel.entity.Amenities;
import nhom8.javabackend.hotel.hotel.entity.HotelImages;
import nhom8.javabackend.hotel.hotel.util.Status;
import nhom8.javabackend.hotel.location.entity.Location;
import nhom8.javabackend.hotel.review.entity.Review;
import nhom8.javabackend.hotel.user.entity.User;

public interface HotelDto {
	public Long getId();

	public String getTitle();

	public String getSlug();

	public String getContent();

	public Status getStatus();

	public String getPrice();

	public String getIsNegotiable();

	public String getPropertyType();

	public String getCondition();

	public int getRating();

	public int getRatingCount();

	public String getContactNumber();

	public String getTermsAndCondition();

	public User getAgent();

	public Amenities getAmenities();

	public HotelImages getCoverPic();

	public Location getLocation();

	public Set<HotelImages> getImages();

	public Set<User> getUsersFavourite();

	public Set<Review> getReviews();
}
