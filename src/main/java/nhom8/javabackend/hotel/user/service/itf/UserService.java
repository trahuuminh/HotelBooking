package nhom8.javabackend.hotel.user.service.itf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nhom8.javabackend.hotel.security.dto.RegisterDto;
import nhom8.javabackend.hotel.user.dto.user.CreateUserDto;
import nhom8.javabackend.hotel.user.dto.user.PagingFormatUserDto;
import nhom8.javabackend.hotel.user.dto.user.UpdateUserDto;
import nhom8.javabackend.hotel.user.dto.user.UserDto;
import nhom8.javabackend.hotel.user.entity.User;
import nhom8.javabackend.hotel.user.entity.UserImage;

public interface UserService  {
	Page<UserDto> findAllUser(Pageable pageable);
	
	PagingFormatUserDto pagingFormat(Page<UserDto> page);
	
	User createUser(CreateUserDto dto);
	
	User updateUser(UpdateUserDto dto);
	
	UserDto getUserDetails(Long id);
	
	void deleteUser(Long id);

	boolean isExistedId(Long userId);

	User addFavouriteHotel(Long hotelId, User user);

	User removeFavouriteHotel(Long hotelId, User user);
	
	UserDto getUserDtoByUsername(String username);
	
	User getUserByEmail(String email);
	
	User getUserByUsername(String username);
	
	User setUserProfilePic(User user,UserImage userImage);
	
	User setUserCoverPic(User user,UserImage userImage);
	
	User getUserByUserId(Long userId);
	
	User register(RegisterDto dto);
}
