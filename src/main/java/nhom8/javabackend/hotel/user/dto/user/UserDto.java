package nhom8.javabackend.hotel.user.dto.user;

import java.util.Set;

import nhom8.javabackend.hotel.booking.entity.Booking;
import nhom8.javabackend.hotel.hotel.entity.Hotel;
import nhom8.javabackend.hotel.user.entity.Message;
import nhom8.javabackend.hotel.user.entity.UserImage;

public interface UserDto {
	public Long getId();
	
	public String getRole();
	
	public String getFirstName();
	
	public String getLastName();
	
	public String getUsername();
	
	public String getEmail();
	
	public String getCellNumber();
	
	public String getDateOfBirth();
	
	public String getGender();
	
	public String getContent();
	
	public String getLanguage();
	
	public String getFacebook();
	
	public String getTwitter();
	
	public String getLinkedin();
	
	public String getInstagram();
	
	public String getPinterest();
	
	public UserImage getProfilePic();
	
	public UserImage getCoverPic();
	
	public Set<Hotel> getListedPost();
	
	public Set<Booking> getBookings();
	
	public Set<Hotel> getFavouritePost();
	
	public Set<Message> getMessages();
}
