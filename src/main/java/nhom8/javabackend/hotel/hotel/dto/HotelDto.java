package nhom8.javabackend.hotel.hotel.dto;

import java.util.Set;

import nhom8.javabackend.hotel.hotel.dto.hotelimages.HotelImagesDto;
import nhom8.javabackend.hotel.hotel.entity.Amenities;
import nhom8.javabackend.hotel.hotel.util.Status;
import nhom8.javabackend.hotel.location.entity.Location;
import nhom8.javabackend.hotel.review.dto.SimpleReviewDto;
import nhom8.javabackend.hotel.user.entity.User;

public interface HotelDto {
	public Long getId();

	public String getTitle();

	public String getSlug();

	public String getContent();

	public Status getStatus();

	public String getPrice();

	public boolean getIsNegotiable();

	public String getCondition();

	public float getRating();

	public int getRatingCount();

	public String getContactNumber();

	public String getTermsAndCondition();

	public User getAgent();

	public Amenities getAmenities();

	public Set<HotelImagesDto> getImages();
	
	public HotelImagesDto getCoverPic();

	public Location getLocation();

	public Set<SimpleReviewDto> getReviews();
	
}
