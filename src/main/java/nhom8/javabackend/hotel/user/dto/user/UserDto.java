package nhom8.javabackend.hotel.user.dto.user;

import java.util.Set;

import nhom8.javabackend.hotel.hotel.dto.HotelDto;
import nhom8.javabackend.hotel.user.dto.userimage.UserImageDto;
import nhom8.javabackend.hotel.user.util.Role;

public interface UserDto {
	public Long getId();
	
	public Role getRole();
	
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
	
	public UserImageDto getProfilePic();
	
	public UserImageDto getCoverPic();
	
	public Set<HotelDto> getListedPost();
	
	public Set<HotelDto> getFavouritePost();
	
	
}
