package nhom8.javabackend.hotel.user.dto.user;

import nhom8.javabackend.hotel.user.dto.userimage.UserImageDto;

public interface UserReviewDto {
	public Long getId();
	
	public String getFirstName();
	
	public String getLastName();
	
	public String getUsername();
	
	public String getEmail();
	
	public String getCellNumber();

	public UserImageDto getProfilePic();
}
